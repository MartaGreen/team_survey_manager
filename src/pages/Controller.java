package pages;

import main.Main;

/**
 * Page controller interface
 */
public interface Controller {
    /**
     * Sets the main to get data about current state of app.
     *
     * @param main The main instance.
     */
    void setMain(Main main);

    /**
     * Set up page, by adding components and actual data
     */
    void setupPage();
}
