describe('Работа с избранным: автосервисы', () => {
    beforeEach(() => {
        cy.authenticate();
        cy.visit('https://www.drive2.ru');
    });

    it('Добавление автосервиса в избранное', () => {
        cy.get('a[href*="/companies/"]').click();

        cy.get('article.c-company-card').first().within(() => {
            cy.get('a.u-card-link').first().invoke('removeAttr', 'target').click();
        });

        cy.get('bookmark-button', {timeout: 10000})
            .should('be.visible')
            .first().click();

        cy.get('button.c-button--primary').contains('Сохранить').click();

        cy.visit('https://www.drive2.ru/my/bookmarks/');
        cy.get('a.u-link-area[href*="/o/reflashteam/"]').should('exist');
        cy.get('[data-slot="bookmarks.counter"]').should('not.contain', '0');
    });

    it('Удаление автосервиса из избранного', () => {
        cy.visit('https://www.drive2.ru/my/bookmarks/');

        cy.get('button[data-action="bookmarks.edit"]').first().click();
        cy.get('button.c-button--block').contains('Удалить').first().click();
        cy.get('button.c-button--primary').contains('Удалить').click();

        cy.get('[data-slot="bookmarks.counter"]').should('contain', '0');
        cy.get('article.c-company-card').should('not.exist');
    });
});
