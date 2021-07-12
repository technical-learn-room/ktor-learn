## Ktor Framework란?
`Ktor Framework`는 주로 코틀린을 대상으로한 `Web Framework`입니다.

특히 라우팅을 하는 부분에서부터 `suspend` 함수로 작성되어 있기 때문에  
쉽게 코루틴을 적용할 수 있는 환경이 마련되어 있고,  
많은 함수들이 `inline function`으로 정의되어 있기 때문에 스프링보다 가벼운 개발이 가능합니다.  
또한 수식 객체 지정 람다와 같은 문법을 통해 읽기 좋은 코드를 작성할 수 있습니다.  

## 애플리케이션 구성
```kotlin
fun main() {
    embeddedServer(Netty, port = 6180, host = "0.0.0.0") {
        routing {...}
    }.start(wait = true)
}
```
`Ktor`의 기본적인 애플리케이션 구성은 다음과 같습니다.  
웹서버, 포트 번호, 호스트 주소를 설정할 수 있게 되어 있고  
라우팅을 설정할 수 있도록 수신 객체 지정 람다를 통해 `Application` 객체의 메소드에 접근할 수 있습니다.  

```kotlin
fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    routing {...}
}
```
이렇게 스프링부트처럼 설정을 애플리케이션 시작 부분에서 담당하지 않고  
라우팅과 관련된 설정도 모듈을 통해 따로 빼놓을 수도 있습니다.  
이런 경우에는 애플리케이션에 대한 설정을 스프링부트에서 `application.yml`에 설정했던 것처럼  
`application.conf` 파일에 설정 정보들을 저장할 수 있습니다.  
```conf
ktor {
    deployment {
        port = 8080
    }
    application {
        modules = [ com.example.ApplicationKt.module ]
    }
}
```

## 라우팅 하기
```kotlin
fun Route.configureLibraryRoute() {
    searchLibrary()
    registerLibrary()
}

private fun Route.searchLibrary() {
    val librarySearchService by closestDI().instance<LibrarySearchService>()

    get(path = "/libraries") {
        call.respond(
            status = HttpStatusCode.OK,
            message = LibraryAllSearchResponse(
                libraries = librarySearchService.searchAll(),
            ),
        )
    }

    get(path = "/libraries/{libraryId}") {
        val libraryId = call.parameters["libraryId"].validateNumberString()

        call.respond(
            status = HttpStatusCode.OK,
            message = librarySearchService.search(libraryId)
        )
    }
}

private fun Route.registerLibrary() {
    val libraryCreationService by closestDI().instance<LibraryCreationService>()

    post(path = "/libraries") {
        val request = call.receive<LibraryCreationRequest>()

        libraryCreationService.create(
            libraryName = request.library.name,
            libraryLocation = request.library.location,
        )

        call.respond(HttpStatusCode.Created)
    }
}
```
라우터를 설정할 때는 `Route`의 확장 함수로 정의하면 됩니다.  
`Route`의 메소드에는 `get()`, `post()`와 같은 `HTTP Method`들이 존재하고  
`path`와 `method`를 통해 `API Router`를 구현할 수 있습니다.  

## Request Handling 하기
`HTTP Method`로 이루어진 메소드는  
`PipelineContext<Unit, ApplicationCall>.(R) -> Unit`을 매개변수로 받습니다.  
여기에는 `call`이라는 변수가 존재하는데 이를 이용해 `Request`와 `Response`를 조작할 수 있습니다.  

예를 들어 `call.receive<>()`를 통해 `Request Body`를 가져올 수 있고,  
`call.parameters[...]`으로 `Path Variable`을 가져올 수 있습니다.  
이외에 다양한 `Request Handling`은 [다음 주소](https://ktor.io/docs/requests.html)에서 확인하실 수 있습니다.  

## Response 보내기
`Response`도 `Request`와 마찬가지로 `call` 변수를 이용해 조작합니다.  
`Response Body` 없이 상태 코드만 전달하고 싶다면  
```kotlin
call.respond(HttpStatusCode.Created)
```
위처럼 사용하면 되고
`Response Body`를 전달하고 싶다면 다음과 같이 하면 됩니다.
```kotlin
call.respond(
    status = HttpStatusCode.OK,
    message = Object(),
)
```

## 예외 처리
```kotlin
fun main() {
    embeddedServer(Netty, port = 6180, host = "0.0.0.0") {

        install(ContentNegotiation) {
            jackson()
        }
        install(CallLogging)
        install(StatusPages) {
            exception<CommonException> { e ->
                call.respond(
                    status = e.httpStatusCode,
                    message = CommonExceptionResponse(
                        error = CommonExceptionResponse.ExceptionAttribute(
                            code = e.errorCode,
                            message = e.errorMessage,
                        )
                    )
                )
            }
        }

        di {
            bindServices()
        }

        routing {
            apiRoute()
        }
    }.start(wait = true)
}
```
스프링에서는 예외 처리를 위한 핸들러를 따로 구축해야했던 반면에  
`Ktor`에서는 애플리케이션을 설정하는 부분에서 이를 제어할 수 있습니다.
위에 보이시는 `install()` 구문에서 `StatusPages`를 가지는 구문이 예외 처리를 담당하는 구문입니다.  

마찬가지 원리로 `CallLogging`은 `Request`를 로깅하는 역할을 하며,  
`ContentNegotiation`은 `JSON`으로 변환할 때 어떤 컨버터를 사용할지를 선택합니다.  

## Dependency Injection
```kotlin
fun DI.MainBuilder.bindServices() {
    bind<LibraryCreationService>() with singleton { LibraryCreationService(libraryDataAccessor) }
}
```
이런 웹 프레임워크를 사용하면서 제어의 흐름을 역전시키기 위해서는  
중간 제어자 (컨테이너)를 통한 `Dependency Injection`이 필요합니다.  
여기서는 `Kodein`이라는 `DI Framework`를 이용하여 `IoC`를 실현합니다.  

사용하는 곳에서는 다음과 같이 위임을 통해 의존성 주입을 받는 객체를 사용할 수 있습니다.  
```kotlin
val libraryCreationService by closestDI().instance<LibraryCreationService>()
```

## Ktor Project Structure
```
src
  ㄴ main
      ㄴ kotlin.com.j.lms
          ㄴ domain
              ㄴ book
                  ㄴ dao
                  ㄴ entity
                  ㄴ exception
                  ㄴ router
                      ㄴ request
                      ㄴ response
                  ㄴ service
              ㄴ library
                  ㄴ dao
                  ㄴ entity
                  ㄴ exception
                  ㄴ router
                      ㄴ request
                      ㄴ response
                  ㄴ service
          ㄴ global
              ㄴ configuration
              ㄴ exception
          ㄴ ApiRoute.kt
          ㄴ Application.kt
      ㄴ resources
          ㄴ application.conf
          ㄴ logback.xml
```

## Library & Framework
- Kotlin 1.5.20
- Ktor 1.6.1
- Kodein 7.6.3
- Logback 1.2.3
