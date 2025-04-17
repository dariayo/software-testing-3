describe('Фильтрация бортжурналов по марке автомобиля', () => {
    const experienceUrl = 'https://www.drive2.ru/experience/';
    const brandSelectSelector = 'select#BrandName';
    const submitButtonSelector = 'button[data-ym-target="exp_find"]';
    const brandToSelect = 'Audi';
    const brandValueToSelect = 'audi';

    before(() => {
        cy.authenticate();
        cy.visit('https://www.drive2.ru');
    });

    it('Должен фильтровать бортжурналы по выбранной марке', () => {
        cy.visit(experienceUrl);

        cy.contains('a', 'Бортжурналы').click();

        cy.get(brandSelectSelector)
            .should('be.visible')
            .invoke('val', brandValueToSelect)
            .trigger('change');

        cy.get(submitButtonSelector)
            .should('be.visible')
            .click();
        cy.get('h1.x-title')
            .should('contain', brandToSelect)
            .and('be.visible');
    });
});
