We inject interface `UserMicroserviceRequest` into ImplementInterfaceController. 
UserMicroserviceRequest - has not implementation (dynamically create class implementing interface in `MicroserviceInterfaceImplementorFactory` )

## Usage:

 - Add `@EnableMicroserviceCommunicator(basePackages = "com.example")` to class in componentScan, annotated with `@Configuration`
 
## Example
To try

`http://localhost:7777/implement_interface/users` will response array;