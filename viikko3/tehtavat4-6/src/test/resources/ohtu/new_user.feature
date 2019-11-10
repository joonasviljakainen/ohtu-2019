Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful when using valid username and password
        Given command new is selected
        When username "rauno" and password "repom1es" are entered
        Then system will respond with "new user registered"

    Scenario: creation fails when trying to register with a username that has been taken and a valid password
        Given command new is selected
        When username "pekka" and password "puota" are entered
        Then system will respond with "new user not registered"

    Scenario: Registration fails when username is too short and password is valid
        Given command new is selected
        When username "pe" and password "blarg" are entered
        Then system will respond with "new user not registered"

    Scenario: Registration fails when username is valid but password too short
        Given command new is selected
        When username "sergei" and password "passwo4" are entered
        Then system will respond with "new user not registered"

    Scenario: Creation when username is valid and password is long enough but only contains characters
        Given command new is selected
        When username "rauno" and password "repomies" are entered
        Then system will respond with "new user not registered"

    Scenario: can log in once account has been created 
        Given user "eero" with password "salainen1" is created
        And command login is selected
        When username "eero" and password "salainen1" are entered
        Then system will respond with "logged in"
