describe('Изменение марки и модели автомобиля', () => {
    before(() => {
        cy.authenticate();
        cy.visit('https://www.drive2.ru');
    });

    it('Должен изменить марку и модель автомобиля', () => {
        cy.get('a[href="/my/r/"]')
            .contains('Мои машины')
            .should('be.visible')
            .click();

        cy.get('a.c-button[href*="/my/r/702162420222660772/"]')
            .contains('Изменить')
            .should('be.visible')
            .click();

        cy.get('select#Brand')
            .should('be.visible')
            .select('aito', {force: true});

        cy.get('select#Model')
            .should('not.have.class', 'is-placeholder')
            .should('not.be.disabled');

        cy.get('select#Model')
            .select('666457154500170565', {force: true});

        cy.get('button[data-slot="form.submit"]')
            .contains('Сохранить')
            .should('be.visible')
            .click();

        cy.get('h1.x-title')
            .should('contain', 'AITO M5')
            .and('be.visible');
    });
});
