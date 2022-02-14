package io.caspercommunity;

import org.apache.camel.main.Main;

/**
 * A Camel Application
 */
public class MainApp {
   
	/**
	 * 
	 * @param args : main rags
	 * @throws Exception : excpetion
	 */
   public static void main(String...args) throws Exception
   {
      Main main = new Main();

      main.configure().addRoutesBuilder(new MyRouteBuilder());
      main.run(args);
   }
}
