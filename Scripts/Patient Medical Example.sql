CREATE TABLE patient_medical_info (
patient_id INT AUTO_INCREMENT PRIMARY KEY, 
reason_of_visit TEXT NOT NULL, 
length_of_visit VARCHAR(10) NOT NULL
);

CREATE TABLE patient_info (
patient_name VARCHAR(20) PRIMARY KEY, 
date_of_entry DATE NOT NULL, 
date_of_leave VARCHAR(15), 
gender VARCHAR(1)
);

INSERT INTO patient_medical_info (reason_of_visit, length_of_visit)
VALUES
("Cardiac Arrest", "TBD"),
("Brain Aneurysm", "3d"),
("Brain Aneurysm", "2d"),
("Cardiac Arrest", "4d"),
("Brain Aneurysm", "2d"),
("Brain Aneurysm", "TBD"),
("Cardiac Arrest", "1w 2d"),
("Cardiac Arrest", "1w");

INSERT INTO patient_info(patient_name, date_of_entry, date_of_leave, gender)
VALUES
("John Smith", '2014-10-27', '2014-10-31', "M"),
("Mary Jane", '2014-10-22', '2014-10-31', "F"),
("Patty Patterson", '2014-10-24', '2014-10-31', "F"),
("Jimmy Jistle", '2014-10-28', '2014-10-31', "M"),
("Tony Tiger", '2014-10-29', '2014-10-31', "M"),
("Chris Campbell", '2014-10-29', '2014-10-31', "M"),
("Fiona Fasterner", '2014-10-25', 'TBD', "F"),
("Horus Harvery", '2014-10-20', 'TBD', "M")
;