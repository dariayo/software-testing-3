const { defineConfig } = require('cypress');
const allureWriter = require("@shelex/cypress-allure-plugin/writer");

module.exports = defineConfig({
    e2e: {
        baseUrl: 'https://www.drive2.ru',
        specPattern: 'cypress/tests/**/*.cy.js',
        supportFile: 'cypress/support/e2e.js',
        video: false,
        screenshotsFolder: 'cypress/screenshots',
        retries: {
            runMode: 2,
            openMode: 0
        },
        setupNodeEvents(on, config) {
            allureWriter(on, config);
            return config;
        },
        env: {
            allure: true,
            allureReuseAfterSpec: true
        },
        chromeWebSecurity: false
    }
});
