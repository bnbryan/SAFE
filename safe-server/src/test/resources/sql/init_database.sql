CREATE TABLE ybj_account (
                             anum  BIGINT NOT NULL COMMENT 'Account number',
                             aname VARCHAR(20) NOT NULL COMMENT 'Account name',
                             adate DATETIME NOT NULL COMMENT 'Date opened',
                             atype VARCHAR(4) NOT NULL COMMENT 'Account Type',
                             cid   BIGINT NOT NULL,
                             adid  BIGINT NOT NULL
);

ALTER TABLE ybj_account
    ADD CONSTRAINT ch_inh_ybj_account CHECK ( atype IN ( 'C', 'L', 'S' ) );

ALTER TABLE ybj_account ADD CONSTRAINT ybj_account_pk PRIMARY KEY ( anum );
ALTER TABLE ybj_account MODIFY anum BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Account number';
CREATE TABLE ybj_address (
                             adid     BIGINT NOT NULL COMMENT 'Address id',
                             adstreet VARCHAR(30) NOT NULL COMMENT 'Street of the address',
                             adcity   VARCHAR(15) NOT NULL COMMENT 'City of the address',
                             adstate  VARCHAR(15) NOT NULL COMMENT 'State of the address',
                             adapt    VARCHAR(6) COMMENT 'Apartment number of the address',
                             adzip    INT NOT NULL COMMENT 'Zip code of  the address'
);

ALTER TABLE ybj_address ADD CONSTRAINT ybj_address_pk PRIMARY KEY ( adid );
ALTER TABLE ybj_address MODIFY adid BIGINT NOT NULL AUTO_INCREMENT COMMENT "Address id";

CREATE TABLE ybj_checking (
                              anum    BIGINT NOT NULL COMMENT 'Account number',
                              ccharge DECIMAL(5, 2) NOT NULL,
                              atype   VARCHAR(4) NOT NULL,
                              abalance DECIMAL(20,2) NOT NULL COMMENT 'Account Balance',
                              cvalid VARCHAR(1) NOT NULL COMMENT 'Account status'
);

ALTER TABLE ybj_checking ADD CONSTRAINT ybj_checking_pk PRIMARY KEY ( anum );

CREATE TABLE ybj_company (
                             comid   BIGINT NOT NULL COMMENT 'Insurance company id',
                             comname VARCHAR(30) NOT NULL COMMENT 'Insurance company''s name',
                             adid    BIGINT NOT NULL
);
CREATE UNIQUE INDEX ybj_company__idx ON
    ybj_company (
                 adid
                 ASC );

ALTER TABLE ybj_company ADD CONSTRAINT ybj_company_pk PRIMARY KEY ( comid );
ALTER TABLE ybj_company MODIFY comid BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Insurance company id';

CREATE TABLE ybj_cust_addr (
                               cid  BIGINT NOT NULL,
                               adid BIGINT NOT NULL
);

ALTER TABLE ybj_cust_addr ADD CONSTRAINT ybj_cust_addr_pk PRIMARY KEY ( cid,
                                                                        adid );
CREATE TABLE ybj_customer (
                              cid    BIGINT NOT NULL COMMENT 'Customer id',
                              clname VARCHAR(10) NOT NULL COMMENT 'Customer''s last name',
                              cfname VARCHAR(10) NOT NULL COMMENT 'Customer''s first name',
                              cemail VARCHAR(255) NOT NULL COMMENT 'Customer''s email',
                              cpassword VARCHAR(64) NOT NULL COMMENT 'Customer''s password',
                              security_question VARCHAR(255) NOT NULL COMMENT 'Security Question',
                              security_answer VARCHAR(255) NOT NULL COMMENT 'Security Answer',
                              cvalid VARCHAR(1) NOT NULL COMMENT 'Customer status valid or not'
);

ALTER TABLE ybj_customer ADD CONSTRAINT ybj_customer_pk PRIMARY KEY ( cid );
ALTER TABLE ybj_customer MODIFY cid BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Customer id';
ALTER TABLE ybj_customer ADD UNIQUE (cemail);

CREATE TABLE ybj_record (
                            rid BIGINT NOT NULL COMMENT 'Record''s id',
                            anum BIGINT COMMENT 'From some account',
                            toanum BIGINT COMMENT 'To some account',
                            ratype   VARCHAR(4) NOT NULL,
                            ramount DECIMAL(10, 2) NOT NULL COMMENT 'Transfer amount',
                            rtime TIMESTAMP NOT NULL COMMENT 'transfer time'
);

ALTER TABLE ybj_record ADD CONSTRAINT ybj_record_pk PRIMARY KEY ( rid );
ALTER TABLE ybj_record MODIFY rid BIGINT NOT NULL AUTO_INCREMENT COMMENT "Record''s id";

CREATE TABLE ybj_admin (
                            aid BIGINT NOT NULL COMMENT 'Admin''s id',
                            username VARCHAR(64) NOT NULL COMMENT 'Admin''s username',
                            password VARCHAR(64) NOT NULL COMMENT 'Admin''s password'
);

ALTER TABLE ybj_admin ADD CONSTRAINT ybj_admin_pk PRIMARY KEY (aid);
ALTER TABLE ybj_admin MODIFY aid BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Admin''s id';
ALTER TABLE ybj_admin
    ADD UNIQUE (username);


CREATE TABLE ybj_insurance (
                               iid      BIGINT NOT NULL COMMENT 'Insurance id',
                               iaccount BIGINT NOT NULL COMMENT 'Insurance account number',
                               ipremium DECIMAL(10, 2) NOT NULL COMMENT 'Yearly insurance premium',
                               comid    BIGINT NOT NULL
);

ALTER TABLE ybj_insurance ADD CONSTRAINT ybj_insurance_pk PRIMARY KEY ( iid );
ALTER TABLE ybj_insurance MODIFY iid BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Insurance id';

CREATE TABLE ybj_loan (
                          anum         BIGINT NOT NULL COMMENT 'Account number',
                          lrate        DECIMAL(4, 2) NOT NULL COMMENT 'Loan rate',
                          lamount      DECIMAL(10, 2) NOT NULL COMMENT 'Loan amount',
                          lmonths      SMALLINT NOT NULL COMMENT 'Loan months',
                          lpayment     DECIMAL(10, 2) NOT NULL COMMENT 'Loan payment',
                          ltype        VARCHAR(4) NOT NULL COMMENT 'Loan type',
                          hyear        DATETIME,
                          hinsurance   DECIMAL(20) COMMENT 'Home insurance account number',
                          iid          BIGINT,
                          stuid        VARCHAR(10) COMMENT 'Student id',
                          stutype      VARCHAR(1) COMMENT 'Student''s type. Grad or undergrad.',
                          stugraddate  DATETIME COMMENT 'Graduation date.',
                          uID        BIGINT,
                          atype        VARCHAR(4) NOT NULL,
                          lvalid       VARCHAR(1) NOT NULL COMMENT 'Loan status valid or not'
);

CREATE TABLE ybj_loan_app (
                          laid          BIGINT NOT NULL COMMENT 'loan application number',
                          cid         BIGINT NOT NULL COMMENT 'customer number',
                          lrate        DECIMAL(4, 2) NOT NULL COMMENT 'Loan rate',
                          lamount      DECIMAL(10, 2) NOT NULL COMMENT 'Loan amount',
                          lmonths      SMALLINT NOT NULL COMMENT 'Loan months',
                          lpayment     DECIMAL(10, 2) NOT NULL COMMENT 'Loan payment',
                          ltype        VARCHAR(1) NOT NULL COMMENT 'Loan type',
                          hyear        DATETIME,
                          hinsurance   DECIMAL(20) COMMENT 'Home insurance account number',
                          laiaccount      BIGINT COMMENT 'Insurance account number',
                          lacomname       VARCHAR(30) COMMENT 'Insurance company''s name',
                          ipremium      DECIMAL(10, 2) COMMENT 'Yearly insurance premium',
                          stuid        VARCHAR(10) COMMENT 'Student id',
                          stutype      VARCHAR(1) COMMENT 'Student''s type. Grad or undergrad.',
                          stugraddate  DATETIME COMMENT 'Graduation date.',
                          uname         VARCHAR(30) COMMENT 'University name',
                          lavalid       VARCHAR(1) COMMENT 'Loan application status valid or not'
);

ALTER TABLE ybj_loan_app ADD CONSTRAINT ybj_loan_app_pk PRIMARY KEY ( laid );
ALTER TABLE ybj_loan_app MODIFY laid BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Loan application id';

ALTER TABLE ybj_loan
    ADD CONSTRAINT ch_inh_ybj_loan CHECK ( ltype IN ( 'HOME', 'L', 'STU' ) );

ALTER TABLE ybj_loan
    ADD CONSTRAINT ybj_loan_exdep CHECK ( ( ltype = 'HOME'
        AND hyear IS NOT NULL
        AND hinsurance IS NOT NULL
        AND iid IS NOT NULL
        AND stuid IS NULL
        AND stutype IS NULL
        AND stugraddate IS NULL
        AND uID IS NULL )
        OR ( ltype = 'L'
            AND hyear IS NULL
            AND hinsurance IS NULL
            AND iid IS NULL
            AND stuid IS NULL
            AND stutype IS NULL
            AND stugraddate IS NULL
            AND uID IS NULL )
        OR ( ltype = 'STU'
            AND hyear IS NULL
            AND hinsurance IS NULL
            AND iid IS NULL
            AND stuid IS NOT NULL
            AND stutype IS NOT NULL
            AND stugraddate IS NOT NULL
            AND uID IS NOT NULL ) );

CREATE UNIQUE INDEX ybj_loan__idx ON
    ybj_loan (
              iid
              ASC );

ALTER TABLE ybj_loan ADD CONSTRAINT ybj_loan_pk PRIMARY KEY ( anum );

CREATE TABLE ybj_savings (
                             anum  BIGINT NOT NULL COMMENT 'Account number',
                             srate DECIMAL(4, 2) NOT NULL COMMENT 'Interest rate.',
                             atype VARCHAR(4) NOT NULL,
                             sbalance DECIMAL(20,2) NOT NULL COMMENT 'Account balance',
                             svalid VARCHAR(1) NOT NULL COMMENT 'Account status'
);

ALTER TABLE ybj_savings ADD CONSTRAINT ybj_savings_pk PRIMARY KEY ( anum );

CREATE TABLE ybj_university (
                                uID BIGINT NOT NULL COMMENT 'University id',
                                uname VARCHAR(30) NOT NULL COMMENT 'University name'
);

ALTER TABLE ybj_university ADD CONSTRAINT ybj_university_pk PRIMARY KEY ( uID );
ALTER TABLE ybj_university MODIFY uID BIGINT NOT NULL AUTO_INCREMENT COMMENT 'University id';


CREATE TABLE account_app (
                              app_id    BIGINT NOT NULL COMMENT 'Application id',
                              cid BIGINT NOT NULL COMMENT 'Customer''s id',
                              type VARCHAR(1) NOT NULL COMMENT 'Account type',
                              income DECIMAL(20,2) NOT NULL COMMENT 'Customer''s annual income',
                              career VARCHAR(255) NOT NULL COMMENT 'Security Answer',
                              status VARCHAR(1) COMMENT 'Application status Pass or Declined'
);

ALTER TABLE account_app ADD CONSTRAINT account_app_pk PRIMARY KEY ( app_id );
ALTER TABLE account_app MODIFY app_id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'University id';
ALTER TABLE account_app ADD constraint check (status in ('P', 'D') );
ALTER TABLE account_app
    ADD CONSTRAINT account_app_ybj_customer_fk FOREIGN KEY (cid)
        REFERENCES ybj_customer (cid);

ALTER TABLE ybj_account
    ADD CONSTRAINT ybj_account_ybj_address_fk FOREIGN KEY ( adid )
        REFERENCES ybj_address ( adid );

ALTER TABLE ybj_account
    ADD CONSTRAINT ybj_account_ybj_customer_fk FOREIGN KEY ( cid )
        REFERENCES ybj_customer ( cid );

ALTER TABLE ybj_checking
    ADD CONSTRAINT ybj_checking_ybj_account_fk FOREIGN KEY ( anum )
        REFERENCES ybj_account ( anum );

ALTER TABLE ybj_company
    ADD CONSTRAINT ybj_company_ybj_address_fk FOREIGN KEY ( adid )
        REFERENCES ybj_address ( adid );

ALTER TABLE ybj_cust_addr
    ADD CONSTRAINT ybj_cust_addr_ybj_address_fk FOREIGN KEY ( adid )
        REFERENCES ybj_address ( adid );

ALTER TABLE ybj_cust_addr
    ADD CONSTRAINT ybj_cust_addr_ybj_customer_fk FOREIGN KEY ( cid )
        REFERENCES ybj_customer ( cid );

ALTER TABLE ybj_insurance
    ADD CONSTRAINT ybj_insurance_ybj_company_fk FOREIGN KEY ( comid )
        REFERENCES ybj_company ( comid );

ALTER TABLE ybj_loan
    ADD CONSTRAINT ybj_loan_ybj_account_fk FOREIGN KEY ( anum )
        REFERENCES ybj_account ( anum );

ALTER TABLE ybj_loan
    ADD CONSTRAINT ybj_loan_ybj_insurance_fk FOREIGN KEY ( iid )
        REFERENCES ybj_insurance ( iid );

ALTER TABLE ybj_loan
    ADD CONSTRAINT ybj_loan_ybj_university_fk FOREIGN KEY ( uID )
        REFERENCES ybj_university ( uID );

ALTER TABLE ybj_savings
    ADD CONSTRAINT ybj_savings_ybj_account_fk FOREIGN KEY ( anum )
        REFERENCES ybj_account ( anum );

ALTER TABLE ybj_record
     ADD CONSTRAINT ybj_record_anum_fk FOREIGN KEY (anum)
        REFERENCES ybj_account (anum);

ALTER TABLE ybj_record
    ADD CONSTRAINT ybj_record_toanum_fk FOREIGN KEY (toanum)
        REFERENCES ybj_account (anum);

ALTER TABLE ybj_loan_app
    ADD CONSTRAINT ybj_loan_app_cid_fk FOREIGN KEY (cid)
        REFERENCES ybj_customer (cid);


ALTER TABLE ybj_savings
    ADD CONSTRAINT ybj_savings_rate CHECK (srate > 0);

ALTER TABLE ybj_loan
    ADD CONSTRAINT ybj_loan_rate CHECK (lrate > 0);

ALTER TABLE ybj_checking
    ADD CONSTRAINT ybj_checking_ccharge CHECK (ccharge >= 0);

ALTER TABLE ybj_loan
    ADD CONSTRAINT ybj_loan_stutype CHECK (stutype IN ('U','G'));

ALTER TABLE ybj_loan
    ADD CONSTRAINT ybj_loan_month CHECK (lmonths > 0);

ALTER TABLE ybj_loan
    ADD CONSTRAINT ybj_loan_amount CHECK (lamount > 0);

ALTER TABLE ybj_loan
    ADD CONSTRAINT ybj_loan_payment CHECK (lpayment > 0);

ALTER TABLE ybj_account
    ADD UNIQUE (cid, atype);

ALTER TABLE ybj_loan
    ADD UNIQUE (stuid);


DELIMITER $$
CREATE TRIGGER arc_fkarc_2_ybj_loan_before_insert
    BEFORE INSERT ON ybj_loan
    FOR EACH ROW
BEGIN
    DECLARE d CHAR(4);
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET d = NULL;

    SELECT a.atype INTO d FROM ybj_account a WHERE a.anum = NEW.anum;

    IF d IS NULL OR d <> 'L' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'FK YBJ_LOAN_YBJ_ACCOUNT_FK in Table YBJ_LOAN violates Arc constraint on Table YBJ_ACCOUNT - discriminator column aTYPE doesn''t have value ''L''';
    END IF;
END$$

CREATE TRIGGER arc_fkarc_2_ybj_loan_before_update
    BEFORE UPDATE ON ybj_loan
    FOR EACH ROW
BEGIN
    DECLARE d CHAR(4);
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET d = NULL;

    SELECT a.atype INTO d FROM ybj_account a WHERE a.anum = NEW.anum;

    IF d IS NULL OR d <> 'L' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'FK YBJ_LOAN_YBJ_ACCOUNT_FK in Table YBJ_LOAN violates Arc constraint on Table YBJ_ACCOUNT - discriminator column aTYPE doesn''t have value ''L''';
    END IF;
END$$

CREATE TRIGGER arc_fkarc_2_ybj_checking_before_insert BEFORE INSERT ON ybj_checking
    FOR EACH ROW
BEGIN
    DECLARE d CHAR(4);
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET d = NULL;

    SELECT a.atype INTO d FROM ybj_account a WHERE a.anum = NEW.anum;

    IF d IS NULL OR d <> 'C' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'FK YBJ_CHECKING_YBJ_ACCOUNT_FK in Table YBJ_CHECKING violates Arc constraint on Table YBJ_ACCOUNT - discriminator column aTYPE doesn''t have value ''C''';
    END IF;
END$$
CREATE TRIGGER arc_fkarc_2_ybj_checking_before_update BEFORE INSERT ON ybj_checking
    FOR EACH ROW
BEGIN
    DECLARE d CHAR(4);
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET d = NULL;

    SELECT a.atype INTO d FROM ybj_account a WHERE a.anum = NEW.anum;

    IF d IS NULL OR d <> 'C' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'FK YBJ_CHECKING_YBJ_ACCOUNT_FK in Table YBJ_CHECKING violates Arc constraint on Table YBJ_ACCOUNT - discriminator column aTYPE doesn''t have value ''C''';
    END IF;
END$$


CREATE TRIGGER arc_fkarc_2_ybj_savings_before_update BEFORE UPDATE ON ybj_savings
    FOR EACH ROW
BEGIN
    DECLARE d CHAR(4);
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET d = NULL;

    SELECT a.atype INTO d FROM ybj_account a WHERE a.anum = NEW.anum;

    IF d IS NULL OR d <> 'S' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'FK YBJ_SAVINGS_YBJ_ACCOUNT_FK in Table YBJ_SAVINGS violates Arc constraint on Table YBJ_ACCOUNT - discriminator column aTYPE doesn''t have value ''S''';
    END IF;
END$$
CREATE TRIGGER arc_fkarc_2_ybj_savings_before_insert BEFORE INSERT ON ybj_savings
    FOR EACH ROW
BEGIN
    DECLARE d CHAR(4);
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET d = NULL;

    SELECT a.atype INTO d FROM ybj_account a WHERE a.anum = NEW.anum;

    IF d IS NULL OR d <> 'S' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'FK YBJ_SAVINGS_YBJ_ACCOUNT_FK in Table YBJ_SAVINGS violates Arc constraint on Table YBJ_ACCOUNT - discriminator column aTYPE doesn''t have value ''S''';
    END IF;
END$$

CREATE TRIGGER ybj_account_adate_legal_before_update BEFORE UPDATE ON ybj_account
    FOR EACH ROW
BEGIN
    IF NEW.adate>NOW() THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'aDATE cannot be later than the current time';
    END IF;
END$$
CREATE TRIGGER ybj_account_adate_legal_before_insert BEFORE INSERT ON ybj_account
    FOR EACH ROW
BEGIN
    IF NEW.adate>NOW() THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'aDATE cannot be later than the current time';
    END IF;
END$$

CREATE TRIGGER ybj_loan_adate_legal_before_insert
    BEFORE INSERT ON ybj_loan
    FOR EACH ROW
BEGIN
    DECLARE accountAdate DATE;

    -- 假设ybj_loan表有一个名为account_id的外键列指向account表的ID
    SELECT adate INTO accountAdate FROM ybj_account WHERE anum = NEW.anum;

    -- 现在使用accountAdate来进行你的逻辑检查
    IF accountAdate > New.stugraddate
    THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'stu info is not legal';
    END IF;
END;
CREATE TRIGGER ybj_loan_adate_legal_before_update
    BEFORE UPDATE ON ybj_loan
    FOR EACH ROW
BEGIN
    DECLARE accountAdate DATE;

    -- 假设ybj_loan表有一个名为account_id的外键列指向account表的ID
    SELECT adate INTO accountAdate FROM ybj_account WHERE anum = OLD.anum;

    -- 现在使用accountAdate来进行你的逻辑检查
    IF accountAdate > New.stugraddate
    THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'stu info is not legal';
    END IF;
END;














INSERT INTO ybj_customer (cid, clname, cfname, cemail, cpassword, security_question, security_answer, cvalid) VALUES
(1, 'Smith', 'John', '123@gmail.com', '$2a$10$n9tdBK.F2kYTfH9bRyh7aOzfRwuUB/P.V3LbyYkJyUhPVFljVG8Za', "What's you mother's name", "Linda", 1),
(2, 'Johnson', 'Emily', 'asde@gmai.com', '$2a$10$n9tdBK.F2kYTfH9bRyh7aOzfRwuUB/P.V3LbyYkJyUhPVFljVG8Za', "What's you mother's name", "Linda", 1),
(3, 'Williams', 'Michael', '2433454@gmail.com', '$2a$10$n9tdBK.F2kYTfH9bRyh7aOzfRwuUB/P.V3LbyYkJyUhPVFljVG8Za', "What's you mother's name", "Linda", 1),
(4, 'Brown', 'Linda', '1234@gmail.com', '$2a$10$n9tdBK.F2kYTfH9bRyh7aOzfRwuUB/P.V3LbyYkJyUhPVFljVG8Za', "What's you mother's name", "Linda", 1),
(5, 'Jones', 'Robert', '2345@gmail.com', '$2a$10$n9tdBK.F2kYTfH9bRyh7aOzfRwuUB/P.V3LbyYkJyUhPVFljVG8Za', "What's you mother's name", "Linda", 1),
(6, 'Miller', 'Patricia', '23456@gmail.com', '$2a$10$n9tdBK.F2kYTfH9bRyh7aOzfRwuUB/P.V3LbyYkJyUhPVFljVG8Za', "What's you mother's name", "Linda", 1),
(7, 'Davis', 'David', '3456@gmail.com', '$2a$10$n9tdBK.F2kYTfH9bRyh7aOzfRwuUB/P.V3LbyYkJyUhPVFljVG8Za', "What's you mother's name", "Linda", 1),
(8, 'Garcia', 'Jennifer', '4567@gmail.com', '$2a$10$n9tdBK.F2kYTfH9bRyh7aOzfRwuUB/P.V3LbyYkJyUhPVFljVG8Za', "What's you mother's name", "Linda", 1),
(9, 'Rodriguez', 'Maria', '5678@gmail.com', '$2a$10$n9tdBK.F2kYTfH9bRyh7aOzfRwuUB/P.V3LbyYkJyUhPVFljVG8Za', "What's you mother's name", "Linda", 1),
(10, 'Wilson', 'James','6789@gmail.com', '$2a$10$n9tdBK.F2kYTfH9bRyh7aOzfRwuUB/P.V3LbyYkJyUhPVFljVG8Za', "What's you mother's name", "Linda", 1),
(11, 'Jesse', 'June', '7555@gmail.com', '$2a$10$n9tdBK.F2kYTfH9bRyh7aOzfRwuUB/P.V3LbyYkJyUhPVFljVG8Za', "What's you mother's name", "Linda", 1),
(12, 'Tom', 'Ma', '12@gmail.com', '$2a$10$n9tdBK.F2kYTfH9bRyh7aOzfRwuUB/P.V3LbyYkJyUhPVFljVG8Za', "What's you mother's name", "Linda", 1);

INSERT INTO ybj_address (adid, adstreet, adcity, adstate, adapt, adzip) VALUES
(1, '123 Main St', 'Downtown', 'CA', NULL, 90001),
(2, '456 Maple Dr', 'Springfield', 'IL', '101', 62704),
(3, '789 Oak Ln', 'Libertyville', 'IL', NULL, 60048),
(4, '101 Pine Ct', 'Metropolis', 'NY', '201', 10001),
(5, '202 Birch Blvd', 'Smallville', 'KS', NULL, 67005),
(6, '2800 Layman Court', 'Alpharetta', 'GA', '302', 30201),
(7, '1471 Frederick Street', 'El Paso', 'TX', '201', 79927),
(8, '455 Stratford Court', 'Raleigh', 'NC', NULL, 27601),
(9, '467 Benson Park Drive', 'Oklahoma City', 'OK', '301', 73107),
(10, '2368 Dogwood Lane', 'Tucson', 'AZ', NULL, 85701),
(11, '2340 Ashton Lane', 'Austin', 'TX', NULL, 78701);


INSERT INTO ybj_company (comid, comname, adid) VALUES
(1, 'SecureInsure', 3),
(2, 'HouseSafe', 4),
(3, 'PropertyProtect', 5),
(4, 'HomeCare', 2),
(5, 'TrustAssure', 1),
(6, 'SecureLive', 6),
(7, 'HouseCover', 7),
(8, 'AssetShield', 8),
(9, 'FirstSafety', 9),
(10, 'ProtectFirst', 10);

INSERT INTO ybj_university (uID, uname) VALUES
(1, 'State University'),
(2, 'Tech Institute'),
(3, 'Central College'),
(4, 'Global University'),
(5, 'Innovative University'),
(6, 'Harmony Institute'),
(7, 'Future College'),
(8, 'Pioneer Institute');



INSERT INTO ybj_cust_addr (cid, adid) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 6),
(6, 7),
(7, 8),
(8, 9),
(9, 10),
(10, 5),
(11, 10),
(12, 11);

INSERT INTO ybj_account (anum, aname, adate, atype, cid, adid) VALUES
(1, 'John Checking', '2023-01-01', 'C', 1, 1),
(2, 'Emily Savings', '2023-02-01', 'S', 2, 2),
(3, 'Michael Loan', '2023-03-01', 'L', 3, 3),
(4, 'Michael Savings', '2023-04-01', 'S', 3, 3),
(5, 'Linda Savings', '2023-05-01', 'S', 4, 4),
(6, 'Robert Savings', '2023-06-01', 'S', 5, 6),
(7, 'Patricia Savings', '2023-07-01', 'S', 6, 7),
(8, 'David Savings', '2023-08-01', 'S', 7, 8),
(9, 'Jennifer Savings', '2023-09-01', 'S', 8, 9),
(10, 'Maria Savings', '2023-10-01', 'S', 9, 10),
(11, 'James Loan', '2023-11-01', 'L', 10, 5),
(12, 'June Checking', '2023-10-01', 'C', 11, 10),
(13, 'June Savings', '2023-10-04', 'S', 11, 10),
(14, 'Ma Loan', '2023-12-05', 'L', 12, 11),
(15, 'John Savings', '2024-01-01', 'S', 1, 1),
(16, 'Emily Loan', '2024-01-20', 'L', 2, 2),
(17, 'Jennifer Loan', '2024-02-01', 'L', 8, 9),
(18, 'Patricia Checking', '2024-02-01', 'C', 6, 7),
(19, 'James Savings', '2024-03-01', 'S', 10, 5),
(20, 'Ma Checking', '2024-03-05', 'C', 12, 11),
(21, 'Linda Loan', '2024-03-06', 'L', 4, 4),
(22, 'Emily Checking', '2024-03-06', 'C', 2, 2),
(23, 'David Checking', '2024-03-10', 'C', 7, 8),
(24, 'David Loan', '2024-03-11', 'L', 7, 8);


INSERT INTO ybj_record (rid, anum, toanum, ratype, ramount, rtime) VALUES
(1, 1, 2, 'C', 1500.00, '2023-09-01 14:30:00'),
(2, 3, 4, 'L',200.00, '2023-09-02 10:00:00'),
(3, 3, NULL, 'L',320.50, '2023-09-03 09:45:00'),
(4, 6, 7, 'S',780.00, '2023-09-03 16:15:00'),
(5, 8, 9, 'S',5000.00, '2023-09-04 08:00:00');


INSERT INTO ybj_checking (anum, ccharge, atype, abalance, cvalid) VALUES
(1, 25.00, 'C', 2300, 'Y'),
(12, 30.00, 'C', 0, 'P'),
(18, 40.00, 'C', 10000, 'Y'),
(20, 32.00, 'C', 25000, 'Y'),
(22, 30.00, 'C', 6000, 'Y'),
(23, 25.00, 'C', 21687, 'Y');

INSERT INTO ybj_savings (anum, srate, atype, sbalance, svalid) VALUES
(2, 1.5, 'S', 52020, 'Y'),
(4, 1.2, 'S', 0, 'P'),
(5, 1.32, 'S', 28421, 'Y'),
(6, 1.3, 'S', 28765, 'Y'),
(7, 1.4, 'S', 248614, 'Y'),
(8, 1.5, 'S', 5622, 'Y'),
(9, 1.6, 'S', 8500, 'Y'),
(10, 2.0, 'S', 4571, 'Y'),
(13, 2.0, 'S', 58741, 'Y'),
(15, 2.0, 'S', 0, 'N'),
(19, 2.0, 'S', 24875, 'Y');

INSERT INTO ybj_insurance (iid, iaccount, ipremium, comid) VALUES
(1, 1003, 600.00, 2),
(2, 1006, 600.00, 4),
(3, 1020, 600.00, 5);


INSERT INTO ybj_loan (anum, lrate, lamount, lmonths, lpayment, ltype, hyear, hinsurance, iid, stuid, stutype, stugraddate, uID, atype, lvalid) VALUES
(3, 4.5, 10000.00, 60, 200.00, 'STU', NULL, NULL, NULL,  '202300001', 'G', '2025-06-01', '1', 'L', 'P'),
(11, 5.5, 5000.00, 50, 100.00, 'STU', NULL, NULL, NULL,  '202200020', 'U', '2026-06-01', '1', 'L', 'Y'),
(14, 3.5, 150000.00, 360, 500.00, 'HOME', '2017-05-01', '100002130', '1', NULL, NULL, NULL, NULL, 'L', 'P'),
(16, 3.5, 130000.00, 360, 800.00, 'HOME', '2016-05-01', '1000232130', '2', NULL, NULL, NULL, NULL, 'L', 'Y'),
(17, 3.5, 200000.00, 360, 1000.00, 'HOME', '2018-05-01', '100242130', '3', NULL, NULL, NULL, NULL, 'L', 'Y'),
(21, 5.5, 6000.00, 50, 100.00, 'STU', NULL, NULL, NULL, '202200031', 'U', '2026-06-01', '3', 'L', 'Y'),
(24, 5.5, 5500.00, 50, 100.00, 'STU', NULL, NULL, NULL, '202200024', 'G', '2026-06-01', '4', 'L', 'Y');


INSERT INTO ybj_admin (aid, username, password) VALUES
(123, 'admin1','$2a$10$iBVWNCuhgmFbF/QXU3WDTOyUjq1JSbvrog/7PB2mvox0/9e.s9DLa');

INSERT INTO account_app (cid, type, income, career) VALUES
(5, 'C', '100000.00', 'student');
INSERT INTO account_app (cid, type, income, career, status)VALUES
(8, 'C', '10000.00', 'student', 'P');