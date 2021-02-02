package org.example;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.xml.DocumentContainer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class JxPathTest {

    private Person person;

    @Before
    public void init() {
        person = new Person();
        Job job = new Job("Climber");
        Address a1 = new Address(56, "king avenue");
        Address a2 = new Address(1, "playa avenue");
        Address homeAddress = new Address(10, "my address");
        List<Address> addressList = List.of(a1, a2);

        person.setFirstName("foo");
        person.setLastName("bar");
        person.setHomeAddress(homeAddress);
        person.setAddressList(addressList);
        person.setJob(job);

        System.out.println("Person : " + person);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void javaBeanPropertyAccessTest() {
        // JXPath uses JavaBeans introspection to enumerate and access JavaBeans properties.

        JXPathContext context = JXPathContext.newContext(person);

        String fName = (String) context.getValue("firstName");

        // JXPath can traverse object graphs
        int sNumber = (int) context.getValue("homeAddress/number");

        List<Address> addressList = (List<Address>) context.getValue("addressList");
        Address address1 = (Address) context.getValue("addressList[1]");

        System.out.println("address1 " + address1);

        Assert.assertEquals("foo", fName);
        Assert.assertEquals(10, sNumber);
        Assert.assertEquals(2, addressList.size());
        Assert.assertEquals("king avenue", address1.getStreet());
    }

    @Test
    public void documentAccessTest() {

        URL resource = getClass().getClassLoader().getResource("catalog.xml");
        Assert.assertNotNull(resource);

        DocumentContainer dc = new DocumentContainer(resource);
        JXPathContext ctx = JXPathContext.newContext(dc);
        Iterator iter = ctx.iterate("//cd/artist");
        //In this case, following API will return DOM object
        //Iterator iter = ctx.selectNodes("//cd/artist").iterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            System.out.println(obj);//object type is String
        }
    }

}
