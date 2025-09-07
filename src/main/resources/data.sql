-- Insert sample patients with all blood groups (idempotent)
INSERT INTO patient (name, dob, email, gender, created_at, blood_group) VALUES
    ('Sai Goutham', '2000-05-15', 'sai@example.com', 'Male', NOW(), 'O_POSITIVE'),
    ('Ananya Sharma', '1999-08-22', 'ananya@example.com', 'Female', NOW(), 'A_POSITIVE'),
    ('Rohit Verma', '2001-01-10', 'rohit@example.com', 'Male', NOW(), 'B_POSITIVE'),
    ('Priya Mehta', '1998-11-30', 'priya@example.com', 'Female', NOW(), 'AB_POSITIVE'),
    ('Arjun Reddy', '2002-07-12', 'arjun@example.com', 'Male', NOW(), 'O_NEGATIVE'),
    ('Kavya Iyer', '1997-03-18', 'kavya@example.com', 'Female', NOW(), 'A_NEGATIVE'),
    ('Manish Gupta', '2001-09-25', 'manish@example.com', 'Male', NOW(), 'B_NEGATIVE'),
    ('Sneha Nair', '1999-12-05', 'sneha@example.com', 'Female', NOW(), 'AB_NEGATIVE')
ON CONFLICT (email) DO NOTHING;


INSERT INTO doctor (name, specialization, email)
VALUES
    ('Dr. Rakesh Mehta', 'Cardiology', 'rakesh.mehta@example.com'),
    ('Dr. Sneha Kapoor', 'Dermatology', 'sneha.kapoor@example.com'),
    ('Dr. Arjun Nair', 'Orthopedics', 'arjun.nair@example.com');


-- truncate appointment;

INSERT INTO appointment (appointment_time, reason, doctor_id, patient_id)
VALUES
    ('2025-07-01 10:30:00', 'General Checkup', 1, 2),
    ('2025-07-02 11:00:00', 'Skin Rash', 2, 2),
    ('2025-07-03 09:45:00', 'Knee Pain', 3, 3),
    ('2025-07-04 14:00:00', 'Follow-up Visit', 1, 1),
    ('2025-07-05 16:15:00', 'Consultation', 1, 4),
    ('2025-07-06 08:30:00', 'Allergy Treatment', 2, 5);