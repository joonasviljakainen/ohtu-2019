Feature:  User can log in with valid username/password-combination

    Scenario: nonexistent user can not login
        Given login is selected
        When incorrect username "jami" and correct password "password1" are entered
        Then user is not logged in and error message is given
