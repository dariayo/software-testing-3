describe('Подписка на пользователя', () => {
    beforeEach(() => {
        cy.authenticate();
        cy.visit('https://www.drive2.ru/users/testprofile/');
    });

    it('Пользователь должен иметь возможность подписаться', () => {
        cy.get('subscribe-button')
            .should('not.have.attr', 'subscribed');

        cy.get('subscribe-button').click();

        cy.get('subscribe-button[subscribed]')
            .should('exist');
    });
});

describe('Отписка от пользователя', () => {
    beforeEach(() => {
        cy.authenticate();
        cy.visit('https://www.drive2.ru/users/testprofile/');

        cy.get('subscribe-button').then(($btn) => {
            if (!$btn.attr('subscribed')) {
                cy.get('subscribe-button').click();
            }
        });
    });

    it('Пользователь должен иметь возможность отписаться', () => {
        cy.get('subscribe-button[subscribed]')
            .should('exist');
        cy.get('subscribe-button[subscribed]').click();
        cy.contains('button', 'Отписаться').click();

        cy.get('subscribe-button')
            .should('not.have.attr', 'subscribed');
    });
});
