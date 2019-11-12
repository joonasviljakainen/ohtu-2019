Feature: user can login with a succesfully created account

    Scenario: user can login with successfully generated account
        Given user with username "lea" with password "salainen1" is successfully created
        And   login is selected
        When  username "lea" and password "salainen1" are given
        Then  user is logged in

    Scenario: user can not login with account that is not successfully created
        Given user with username "aa" and password "bad" is tried to be created
        And   login is selected
        When  non-existing username "aa" and password "bad" are given
        Then  user is not logged in and error message is given