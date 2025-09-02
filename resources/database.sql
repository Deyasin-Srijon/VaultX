-- CREATE DATABASE vaultx;
use vaultx;

CREATE TABLE address(
    addr_id INT AUTO_INCREMENT,
    city VARCHAR(30) NOT NULL,
    country VARCHAR(30) DEFAULT 'India',
    state VARCHAR(30),
    PRIMARY KEY(addr_id)
);

CREATE TABLE atm(
    atm_id INT AUTO_INCREMENT,
    card_no CHAR(16) NOT NULL UNIQUE,
    cvv CHAR(3) NOT NULL,
    exp_date VARCHAR(10) NOT NULL,
    pincode VARCHAR(6) NOT NULL,
    PRIMARY KEY(atm_id)
);

CREATE TABLE netbanking(
    banking_id INT AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL,
    user_password VARCHAR(13) NOT NULL,
    pincode CHAR(6) NOT NULL,
    PRIMARY KEY(banking_id)
);

CREATE TABLE transactions(
	transaction_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    trans_date DATE DEFAULT (CURRENT_DATE),
    trans_time TIME DEFAULT (CURRENT_TIME)
);

CREATE TABLE users (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    user_password VARCHAR(13),
    address_id INT,
    phn_no VARCHAR(15),
    dob DATE,
    email VARCHAR(255) UNIQUE,
    aadhar_no VARCHAR(12) UNIQUE,
    FOREIGN KEY (address_id) REFERENCES address(addr_id)
);

CREATE TABLE accounts (
    acc_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    account_no VARCHAR(10) UNIQUE,
    ifsc_code VARCHAR(11) NOT NULL,
    branch_name VARCHAR(30) NOT NULL,
    account_type ENUM('savings', 'current') NOT NULL,
    profile_password VARCHAR(13) NOT NULL,
    atm_id INT,
    banking_id INT,
    amount DECIMAL(15,2) DEFAULT 0.00,
    current_status ENUM('active', 'deactivated') NOT NULL DEFAULT 'active',
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (atm_id) REFERENCES atm(atm_id) ON DELETE SET NULL,
    FOREIGN KEY (banking_id) REFERENCES netbanking(banking_id) ON DELETE SET NULL
);

CREATE TABLE transaction_entries (
    entry_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    transaction_id BIGINT NOT NULL,
    account_id BIGINT NOT NULL,
    entry_type ENUM('debit', 'credit') NOT NULL,
    tran_mode ENUM('Netbanking', 'ATM') NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (transaction_id) REFERENCES transactions(transaction_id) ON DELETE CASCADE,
    FOREIGN KEY (account_id) REFERENCES accounts(acc_id) ON DELETE CASCADE
);