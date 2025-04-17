describe('Просмотр списка подписок', () => {
    beforeEach(() => {
        cy.authenticate();
        cy.visit('https://www.drive2.ru');
    });

    it('Пользователь должен видеть список подписок', () => {
        cy.navigateToPersonalArea();
        cy.get('a[href*="/following"]').contains('человек в подписках').click();

        cy.contains('h1', 'Мои подписки').should('exist');

        cy.get('.x-box.o-f, .c-empty-content__header').then(($el) => {
            if ($el.hasClass('x-box')) {
                cy.wrap($el).should('exist');
            } else {
                cy.wrap($el).should('contain', 'Подписок пока нет');
            }
        });
    });
});
