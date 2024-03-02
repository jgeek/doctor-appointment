# Doctor Appointment

## Architecture

In this project I tried to implement Port and Adapter architecture and somehow do DDD practices.
By the way the code need some refactoring.

## Tests

All test cases are implemented. Unit, integration and some system tests are written.
<br/> **ArchUnit** library is used to check the dependency between **hexagon** layers.

## UI

I designed UI part with the help of Spring MVC and Thymeleaf, but I regret. I could do it easier with
client side technologies such as react js. By the way error handling is not implemented well. 
Because I was far from UI development.
<br/>Application has 3 pages:

- [ ] http://localhost:8080/appointment/times:
  <br/>Page for taking appointment by patients. They can filter times by date and then take a time.
- [ ] http://localhost:8080/appointment/clinic
  <br/> In this page the doctor can open the times for his patients by providing the start and end time.
  <br/>Also, he can see the previously created times and filter them by date.
  <br/> He also can remove the not taken times which already created.
- [ ] http://localhost:8080/appointment/patient
  <br/> In this page, patient can view their appointment by providing their phone number.
