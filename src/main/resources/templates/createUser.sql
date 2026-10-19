CREATE USER 'F26A'@'localhost' IDENTIFIED BY '1234';

GRANT ALL PRIVILEGES ON BankApp.* TO 'F26A'@'localhost';

FLUSH PRIVILEGES;

    SELECT user, host
    FROM mysql.user
    WHERE user = 'F26A';