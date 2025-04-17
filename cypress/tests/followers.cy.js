describe('Просмотр списка подписчиков', () => {
    beforeEach(() => {
        cy.authenticate();
        cy.visit('https://www.drive2.ru');
    });

    it('Открывается список подписчиков', () => {
        cy.navigateToPersonalArea();
        cy.get('a[href*="/followers"]').click();

        cy.contains('h1', 'Мои подписчики').should('be.visible');

        cy.get('.c-empty-content__header, .x-box').then(($el) => {
            if ($el.hasClass('c-empty-content__header')) {
                cy.wrap($el).should('contain', 'Подписчиков пока нет');
            } else {
                cy.wrap($el).should('contain', 'подписчик');
            }
        });
    });
});
