describe('Вступление в сообщество', () => {
    const communityName = 'Сделай Сам';
    const communityId = '342';

    beforeEach(() => {
        cy.authenticate();
        cy.visit('https://www.drive2.ru');
    });

    it('Пользователь может вступить в сообщество', () => {
        cy.get('a[href*="/communities/"]').contains('Сообщества').click();
        cy.get('a[href*="/communities/search"]').contains('Все сообщества').click();
        cy.get(`a[href*="/communities/${communityId}/"]`).contains(communityName).click();
        cy.get('button[data-action="community.join"]')
            .contains('Хочу вступить')
            .click();

        cy.get('button[data-action="community.leave"]')
            .should('contain', 'Покинуть')
            .and('be.visible');

        cy.get('a[href*="/communities/"]').contains('Сообщества').click();
        cy.get(`a[href*="/communities/${communityId}/"]`)
            .contains(communityName)
            .should('be.visible');
    });

    it('Пользователь может выйти из сообщества', () => {
        cy.get('a[href*="/communities/"]').contains('Сообщества').click();
        cy.get(`a[href*="/communities/${communityId}/"]`)
            .contains(communityName)
            .click();
        cy.get('button.c-button--s[data-action="community.leave"]')
            .should('be.visible')
            .and('contain', 'Покинуть')
            .click();
        cy.get('button.c-button--primary.c-button--block')
            .should('be.visible')
            .and('contain', 'Покинуть')
            .click({force: true});
    });
});
