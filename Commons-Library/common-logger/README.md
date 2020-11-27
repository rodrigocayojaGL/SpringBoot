# Common Logger (Aspect Based)

This library levarages the Spring AOP capabilities to produce a consitent logging within the Spring AOP limits. This limits restrict the usage of this library to the methods of the beans that are managed by the Spring Framework (more info [Spring Core Technologies: Aspect Oriented Programming with Spring](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop)) . Within this limits, its possible to:
- Avoid contaminating the source code with log statements, requiring to add the corresponding dependency, and creating a single and simple component class. 
- Provide timing of each method execution
- Offer a `@Loggable` anotatation to log all the parameters values
- Expose the micrometer metrics via JMX or others

## Capabilities:

### Method Tracing

This capability exposes when the method is invocked and when completes its execution without exposing any parameter or value.

The methods exposed are the ones that correspond to the class that maches the Pointcut on the [config Class](#config-class). 

Example: 
```
2020-06-19 11:23:58.605  INFO 8788 --- [nio-8080-exec-1] java.util.Random : Entering: Random.nextInt(..)
...
2020-06-19 11:23:58.614  INFO 8788 --- [nio-8080-exec-1] java.util.Random : Exiting: Random.nextInt(..) returning: java.lang.Integer
``` 
```
2020-06-19 11:41:33.594  INFO 8788 --- [nio-8080-exec-4] gl.poc.service.ItemService : Entering: ItemService.createItem(..)
...
2020-06-19 11:41:33.595  WARN 8788 --- [nio-8080-exec-4] gl.poc.service.ItemService : Exiting: ItemService.createItem(..) Exception: javax.activity.InvalidActivityException
```

> Its possible to disable this capability by setting `gl.logger.traced.enabled=false` in the `application.properties`

### Method Timinig

This capability exposes the time that an metod required to complete its execution. This capability leverages the micrometer framework included in the Spring framework and is posible to expose its metric to different metric handlers.

The methods exposed are the ones that correspond to the class that maches the Pointcut on the [config Class](#config-class). 

Example:
```
...
2020-06-19 11:41:34.088  INFO 8788 --- [nio-8080-exec-5] gl.poc.controler.WebApiControl : Timed: WebApiControl.addItem(..) 2ms
```

> Its possible to disable this capability by setting `gl.logger.timed.enabled=false` in the `application.properties`

### Method Prameter Invocation 

This capability exposes the paramenter values and the return value of a method.

The methods exposed are the ones that are annotated with the annotation **Loggable**, and all parameter are shown using its toString() method.

Example:
```
2020-06-19 11:56:33.392  INFO 13084 --- [nio-8080-exec-2] g.p.c.ItemModelToEntityConverter : Parameters for ItemModelToEntityConverter.convert(..)
2020-06-19 11:56:33.392  INFO 13084 --- [nio-8080-exec-2] g.p.c.ItemModelToEntityConverter : -> Item(id=0, name=nails, group=top-shelf)
...
2020-06-19 11:56:33.392  INFO 13084 --- [nio-8080-exec-2] g.p.c.ItemModelToEntityConverter : Returning Value ItemModelToEntityConverter.convert(..): ItemDTO(id=0, name=nails, sector=top-shelf)
```
```
2020-06-19 11:59:53.216  INFO 11160 --- [nio-8080-exec-3] gl.poc.service.ItemService : Parameters for ItemService.createItem(..)
2020-06-19 11:59:53.216  INFO 11160 --- [nio-8080-exec-3] gl.poc.service.ItemService : -> ItemDTO(id=0, name=nails, sector=top-shelf)
...
2020-06-19 11:59:53.217  WARN 11160 --- [nio-8080-exec-3] gl.poc.service.ItemService : Excption at ItemService.createItem(..): InvalidActivityException
```

> Its possible to disable this capability by setting `gl.logger.parameter.enabled=false` in the `application.properties`

### Metrics registry

The **methos timing** capability uses the micrometer framework to mesure the method execution time, using the method signature as **identifier** and the return value class as **result**.
Also implements the JMX registry to allow the data to be accessed from a JMX capable application like JConsole, Mission Control to use this information.

> Its possible to disable this capability by setting `gl.logger.registry.jmx=false` in the `application.properties`

### Extentions:

This library is capable to be customized for each application and projects:

#### Adding a metrics registry:

To add an other metric is as simple as creating a configurator class that is used by the Spring Framework where it ads a new registry to the global metric as shown in this exmaple:
```
@Configuration
public class JmxConfiguration {

	public JmxConfiguration() {
		Metrics.addRegistry(new JmxMeterRegistry(new JmxConfig() {
			@Override
			public String get(String s) {
				return null;
			}
		}, Clock.SYSTEM));
	}

} 
```
> This class implmementation depends purely on the Meter Registry that will be used. More information: [Micrometer Documentation](https://micrometer.io/docs)

## Usage:

### Import:

Add the depency:
```
<dependency>
 	<groupId>gl.common</groupId>
 	<artifactId>common-logger</artifactId>
 	<version>0.1.0</version>
</dependency>
```

### ConfigClass:

For a seameless integration to the spting project, this library requieres a configuration component:

| Property | Description |  In Example |
| -------- | -------- | -------- |
| `Pointcut` | This specifies the places where the library should be used to expose the diferent information. For more info on its syntax [Introduction to Pointcut Expressions in Spring](https://www.baeldung.com/spring-aop-pointcut-tutorial) | `within(my.basepackage..*)` this instructs to cover all clases that are inside the package `my.basepackage` and its subpackages | 

Create a config class within your project:

```
@Component
@Aspect
public class LoggerConfigsImpl extends LoggerAspectConfig {

	@Pointcut("within(my.basepackage..*)") 
	public void loggerPointcut() {
	}
}
```

### Properties:

These are optional and allow to enable or disable different capabilites of the logger:

| Property | Description | Default | 
| -------- | -------- | -------- |
| `gl.logger.traced.enabled` | Enables the Tracing processor for the aspect | `true` |
| `gl.logger.timed.enabled` | Enables the Timing processor for the aspect | `true` |
| `gl.logger.parameter.enabled` | Enables the Loggable annotation to be processed | `true` |
| `gl.logger.registry.jmx` | Enables the JMX Registry for Micrometer | `true` |

### Anotations:

Its possible to log all the parameters and return values of a method call via the **Loggable** annotation like in this example:

```
@PostMapping("/item")
@Loggable
public void addItem(@RequestBody Item item) throws Exception
{
	is.createItem(converter.convert(item, ItemDTO.class));
}
```

## Notes:

- This library is expected behave witout any side-effect, so it should not impact the application behavior its pressence or its missess.
- As based on Spring AOP, only has effect on the methods of instances resolved by Spring Invertion of Control Mechanizm (example, by `@Autowired`).