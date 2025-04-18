describe('Просмотр списка подписчиков', () => {
    beforeEach(() => {
        cy.authenticate();
        cy.visit('https://www.drive2.ru');
    });

    it('Открывается список подписчиков', () => {
        cy.navigateToPersonalArea();

        cy.xpath('//a[contains(@href, "/followers")]').click();

        cy.xpath('//h1[contains(text(), "Мои подписчики")]').should('be.visible');

        cy.xpath('//*[contains(@class, "c-empty-content__header") or contains(@class, "x-box")]')
            .then(($el) => {
                if ($el.hasClass('c-empty-content__header')) {
                    cy.wrap($el).should('contain', 'Подписчиков пока нет');
                } else {
                    cy.wrap($el).should('contain', 'подписчик');
                }
            });
    });
});
