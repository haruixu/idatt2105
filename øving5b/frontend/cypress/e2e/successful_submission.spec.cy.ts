describe('template spec', () => {
  beforeEach(() => {
    cy.visit('/forms')
  });

  it('passes', () => {
    cy.get('#name-field').type('name')
    cy.get('#email-field').type('name@mail.com')
    cy.get('#message-field').type('message')

    cy.get('#submitBtn').click();
    cy.contains("h1", "This is home");
  })
})