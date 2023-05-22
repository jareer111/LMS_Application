# LMS_Application   
**LMS application DB schema**
![LMS_DB_schema](https://github.com/jareer111/LMS_Application/assets/113532802/ab1f6ad9-7615-45ff-9b99-3270b5ce100c)


**___________________________________________________________________**

                               **This is  a guide to using the LMS application.**

**1. User CRUD Operations:**
   - Create: Implement a method to create a new user by providing necessary details such as username, password, email, etc. Save the user entity in the database.
   - Read: Implement a method to retrieve user information based on user ID or username.
   - Update: Implement a method to update user details such as password, email, etc.
   - Delete: Implement a method to delete a user based on the user ID or username.

**2. University CRUD Operations:**
   - Create: Implement a method to create a new university by providing necessary details such as name, location, etc. Save the university entity in the database.
   - Read: Implement a method to retrieve university information based on the university ID or name.
   - Update: Implement a method to update university details such as location, contact information, etc.
   - Delete: Implement a method to delete a university based on the university ID or name.

**3. Faculty CRUD Operations:**
   - Create: Implement a method to create a new faculty by providing necessary details such as name, university ID, etc. Save the faculty entity in the database.
   - Read: Implement a method to retrieve faculty information based on the faculty ID or name.
   - Update: Implement a method to update faculty details such as name, university ID, etc.
   - Delete: Implement a method to delete a faculty based on the faculty ID or name.

**4. Group CRUD Operations:**
   - Create: Implement a method to create a new group by providing necessary details such as name, faculty ID, etc. Save the group entity in the database.
   - Read: Implement a method to retrieve group information based on the group ID or name.
   - Update: Implement a method to update group details such as name, faculty ID, etc.
   - Delete: Implement a method to delete a group based on the group ID or name.

**5. Journal CRUD Operations:**
   - Create: Implement a method to create a new journal by providing necessary details such as title, date, etc. Save the journal entity in the database.
   - Read: Implement a method to retrieve journal information based on the journal ID or title.
   - Update: Implement a method to update journal details such as title, date, etc.
   - Delete: Implement a method to delete a journal based on the journal ID or title.

**6. Subject CRUD Operations:**
   - Create: Implement a method to create a new subject by providing necessary details such as name, faculty ID, etc. Save the subject entity in the database.
   - Read: Implement a method to retrieve subject information based on the subject ID or name.
   - Update: Implement a method to update subject details such as name, faculty ID, etc.
   - Delete: Implement a method to delete a subject based on the subject ID or name.

**7. Assign Subject to Journal:**
   - Implement a method to associate a subject with a journal. This can be done by adding a subject ID as a foreign key in the journal table.

**8. Assign Students to Journal:**
   - Implement a method to assign students to a journal. This can be done by adding a student ID as a foreign key in the journal table.

**9. Assign Marks to Students:**
   - Implement a method to assign marks to students for a specific journal. This can be done by adding a mark field in the journal-student mapping table.

**Note:** The above steps provide a general outline for implementing CRUD operations based on the given database creation sequence. The exact implementation details may vary based on your project's specific requirements and the technology stack you are using.
