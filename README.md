# Casper BLOCKCHAIN component
Since Camel 3.14

Both producer and consumer are supported.

The Casper blockchain component uses the Casper SDK API and allows you to interact with Casper Network nodes

Maven users will need to add the following dependency to their pom.xml for this component:

```java
<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-casper</artifactId>
    <version>x.x.x</version>
    <!-- use the same version as your Camel core version -->
</dependency>
```

# URI FORMAT
```java
casper://<local/remote host:port or local IPC path>[?options]
```

# CONFIGURING OPTIONS

Camel components are configured on two separate levels:

- component level
- endpoint level

# CONFIGURING COMPONENT OPTIONS

The component level is the highest level which holds general and common configurations that are inherited by the endpoints. For example a component may have security settings, credentials for authentication, urls for network connection and so forth.

Some components only have a few options, and others may have many. Because components typically have pre configured defaults that are commonly used, then you may often only need to configure a few options on a component; or none at all.

Configuring components can be done with [the Component DSL](https://camel.apache.org/manual/component-dsl.html), in a configuration file (application.properties|yaml), or directly with Java code.

# CONFIGURING ENDPOINT OPTIONS

Where you find yourself configuring the most is on endpoints, as endpoints often have many options, which allows you to configure what you need the endpoint to do. The options are also categorized into whether the endpoint is used as consumer (from) or as a producer (to), or used for both.

Configuring endpoints is most often done directly in the endpoint URI as path and query parameters. You can also use the [Endpoint DSL](https://camel.apache.org/manual/Endpoint-dsl.html) as a type safe way of configuring endpoints.

A good practice when configuring options is to use [Property Placeholders](https://camel.apache.org/manual/using-propertyplaceholder.html), which allows to not hardcode urls, port numbers, sensitive information, and other settings. In other words placeholders allows to externalize the configuration from your code, and gives more flexibility and reuse.

The following two sections lists all the options, firstly for the component followed by the endpoint.

# COMPONENT OPTIONS
The Casper Blockchain component supports 10 options, which are listed below.

| Name | Description | Default | Type |
|---|---|---|---|
| `configuration (producer)` | `Default configuration.` |  | CasperConfiguration |
| `operation (common)` | `Operation to use.` |  |  |
| `casperService (common)` | `Casper RPC API ` |  |  CasperService|
| `deployHash (producer)` | `Hash of a block in the blockchain ` |  |  String|
| `blockHeight (producer)` | `Height of a block in the blockchain ` |  |  String|
| `publicKey (producer)` | `Account publick key  ` |  |  String|
| `key (producer)` | `casper_types::Key as formatted string ` |  |  String|
| `path (producer)` | `Path components starting from the key as base ` |  |  String|
| `stateRootHash (producer)` | `state_Root_Hash : an identifier of the current network state ` |  |  String|
| `purseUref (producer)` | `purseUref : URef of an  account main purse` |  |  String|


# ENDPOINT OPTIONS

The Casper Blockchain endpoint is configured using URI syntax:

```java
casper:nodeUrl
```

with the following path and query parameters:

| Name | Description | Default | Type |
|---|---|---|---|
| `nodeUrl (common)` | `Required, sets the node url.` |  | String |


# QUERY PARAMETERS (10 PARAMETERS)

| Name | Description | Default | Type |
|---|---|---|---|
| `configuration (producer)` | `Default configuration.` |  | CasperConfiguration |
| `operation (common)` | `Operation to use.` |  |  |
| `casperService (common)` | `Casper RPC API ` |  |  CasperService|
| `deployHash (producer)` | `Hash of a block in the blockchain ` |  |  String|
| `blockHeight (producer)` | `Height of a block in the blockchain ` |  |  String|
| `publicKey (producer)` | `Account publick key  ` |  |  String|
| `key (producer)` | `casper_types::Key as formatted string ` |  |  String|
| `path (producer)` | `Path components starting from the key as base ` |  |  String|
| `stateRootHash (producer)` | `state_Root_Hash : an identifier of the current network state ` |  |  String|
| `purseUref (producer)` | `purseUref : URef of an  account main purse` |  |  String|


# SAMPLES

consume BLOCK_ADDED events from Casper SSE and send block hashes to a jms queue :
```java

from("casper://127.0.0.1:9999events/main?operation=BLOCK_ADDED")
.jsonpath("$blockAdded.block_hash", false, String.class, "block")
.to("jms:queue:new_blocks");

```
Use the block hash to retrieve the block transfers:

```java
from("jms:queue:new_blocks")
.setHeader(BLOCK_HASH, body())
.to("casper://127.0.0.1:7777?operation=BLOCK_TRANSFERS");

```

# SPRING BOOT AUTO-CONFIGURATION
TODO

