package day6;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FirstTest {

    static Playwright playwright;
    static Page page;

    static Browser browser;

    String name;

    @BeforeAll
    public static void start() {
        System.out.println("I am in the beginning of everything!");
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    public static void end() {
        System.out.println("I am in the end of everything!");
        browser.close();
    }


    @BeforeEach
    public void init() {
        System.out.println("I am going to start a new test");
        page = browser.newContext().newPage();
    }

    @AfterEach
    public void deInit() {
        System.out.println("I am at the end of the current Test");
        page.close();
    }

    @Test
    @DisplayName("Checking Customer balance")
    public void testSomething() {

        System.out.println("Test Number 1");



        // static -> you don't need an object of Assertions
        assertEquals(0, 0);



        // Overloaded takes different arguments


    }


    @Test
    public void testSomething1() {
        System.out.println("Test Number 2");
        assertAll(
                () -> {
                    assertEquals(0, 1);
                },
                () -> {
                    System.out.println("Test");
                    assertEquals(0, 0);
                });

    }

    @Test
    public void assertThatSomethingFails() {

        System.out.println("Test Number 3");


        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            int[] numbers = new int[10];
            System.out.println(numbers[15]); // indexOutOfBounds
        });


    }

    @Test
    @Disabled("This feature is not yet implemented")
    public void testNumber4() {
        System.out.println("Test Number 4");
        Assertions.fail("Failing");
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testParams(int i) {
        Person person = new Person(i);
        assertEquals(person.age, i);
    }


    record Person(int age) {

    }
}
