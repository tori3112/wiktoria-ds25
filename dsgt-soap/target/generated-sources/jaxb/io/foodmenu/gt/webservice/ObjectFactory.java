//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2025.03.20 at 11:45:52 AM CET 
//


package io.foodmenu.gt.webservice;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the io.foodmenu.gt.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: io.foodmenu.gt.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetMealRequest }
     * 
     */
    public GetMealRequest createGetMealRequest() {
        return new GetMealRequest();
    }

    /**
     * Create an instance of {@link GetMealResponse }
     * 
     */
    public GetMealResponse createGetMealResponse() {
        return new GetMealResponse();
    }

    /**
     * Create an instance of {@link Meal }
     * 
     */
    public Meal createMeal() {
        return new Meal();
    }

    /**
     * Create an instance of {@link GetLargestMealRequest }
     * 
     */
    public GetLargestMealRequest createGetLargestMealRequest() {
        return new GetLargestMealRequest();
    }

    /**
     * Create an instance of {@link GetLargestMealResponse }
     * 
     */
    public GetLargestMealResponse createGetLargestMealResponse() {
        return new GetLargestMealResponse();
    }

    /**
     * Create an instance of {@link GetCheapestMealRequest }
     * 
     */
    public GetCheapestMealRequest createGetCheapestMealRequest() {
        return new GetCheapestMealRequest();
    }

    /**
     * Create an instance of {@link GetCheapestMealResponse }
     * 
     */
    public GetCheapestMealResponse createGetCheapestMealResponse() {
        return new GetCheapestMealResponse();
    }

    /**
     * Create an instance of {@link AddOrderRequest }
     * 
     */
    public AddOrderRequest createAddOrderRequest() {
        return new AddOrderRequest();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link AddOrderResponse }
     * 
     */
    public AddOrderResponse createAddOrderResponse() {
        return new AddOrderResponse();
    }

}
