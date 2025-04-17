describe('Поиск по блогу', () => {
    beforeEach(() => {
        cy.authenticate();
        cy.visit('https://www.drive2.ru');
    });

    it('Пользователь может выполнить поиск по личному блогу', () => {
        const searchQuery = 'Тестовый';

        cy.navigateToPersonalArea();
        cy.get('a[href*="/blog/"]').contains('Личный блог').click();

        cy.get('input[name="query"]')
            .should('have.attr', 'placeholder', 'Что ищем?')
            .type(searchQuery);

        cy.get('button').contains('Найти').click({force: true});

        cy.get('.js-entity .c-post-preview__title')
            .should('exist')
            .first()
            .within(() => {
                cy.get('a').should('contain.text', searchQuery);
            });
    });
});
