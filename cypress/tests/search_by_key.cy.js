describe('Поиск объявления по ключевому слову', () => {
    beforeEach(() => {
        cy.authenticate();
        cy.visit('https://www.drive2.ru');
    });

    it('Поиск объявлений по ключевому слову "акустика"', () => {
        const searchKeyword = 'акустика';
        cy.get('a[href*="/market/"]')
            .contains('Барахолка')
            .should('be.visible')
            .click();

        cy.url().should('include', '/market');
        cy.get('.js-market-search').should('be.visible');

        cy.get('.js-market-search')
            .clear()
            .type(searchKeyword)
            .type('{enter}');

        cy.get('.offer-card__title')
            .first()
            .invoke('text')
            .should('match', /акустик/i);
    });
});
