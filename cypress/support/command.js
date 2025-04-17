Cypress.Commands.add('authenticate', () => {
    cy.fixture('authCookies.json').then((cookies) => {
        cookies.forEach(cookie => {
            cy.setCookie(cookie.name, cookie.value, {
                domain: cookie.domain,
                path: cookie.path,
                secure: cookie.secure,
                httpOnly: cookie.httpOnly,
            });
        });
    });
});

Cypress.Commands.add('navigateToPersonalArea', () => {
    cy.get('a.c-top__userpic').should('be.visible').click();
});
