# Casper Camel Connector
Since Camel 3.14

The Casper blockchain component uses the Casper Java SDK : https://github.com/syntifi/casper-sdk and allows you to interact with Casper nodes.

Both producer and consumer are supported.

To use casper Camel component ,add the following dependency maven pom.xml file. for this component:

```java
<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-casper</artifactId>
    <version>${camel.version}</version>
 </dependency>
```

camel.version  : version of camel-core   component

URI Format
casper://<local/remote host:port or local IPC path>[?options]

## Configuring Casper component

The CasperConfiguration class can be used to set initial properties configuration to the component instead of passing it as query parameter. 

The following listings shows how to set the component to be used in your routes.

* Java

```java
public void configure() {
    final CasperConfiguration configuration = new CasperConfiguration();
    final CasperComponent component = new CasperComponent();
    configuration.setUrl(nodeUrl);
    component.setConfiguration(configuration);
    getContext().addComponent("casper", component);

    from("direct:start")
      .to("casper?operation=last_block");
}

```

* Spring configuration


## Producer Example
Producer performs RPC calls to Casper blockchaine like querying blocks, deploys, accounts.

## Consumer Example
Consumer polls Casper Event Stream Server (port 9999) with operations: