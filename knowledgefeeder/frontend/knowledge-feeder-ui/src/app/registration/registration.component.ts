import { UserModel } from './userModel';
import { FormControl, FormGroup } from '@angular/forms';
import { UserService } from '../user.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { InterestService } from '../interest.service';
import { InterestDto } from './interestDto';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {
  registerForm: FormGroup;
  firstName: FormControl = new FormControl('');
  lastName: FormControl = new FormControl('');
  emailAddress: FormControl = new FormControl('');
  selectedInterests = new FormControl('');
  selectedInterestIds: string[] = [];
  allInterests: Array<InterestDto> = [];
  interestNames: Array<string> = [];

  constructor(
    private userService: UserService,
    private interestService: InterestService,
    private _snackBar: MatSnackBar
  ) {
    this.registerForm = new FormGroup({
      firstName: this.firstName,
      lastName: this.lastName,
      emailAddress: this.emailAddress,
      selectedInterests: this.selectedInterests,
    });
  }
  ngOnInit(): void {
    this.clearData();
    this.getInterests();
    this.selectedInterests.valueChanges.subscribe((userSelectedinterest) => {
      // called everytime when form control value is updated
      console.log('value', userSelectedinterest);
      if (this.selectedInterests.value) {

        let jsonData = JSON.stringify(this.selectedInterests.value);
        let propertyValues = JSON.parse(jsonData);
        console.log('propertyValues', propertyValues);
        this.interestNames = [];
        this.selectedInterestIds=[]
        propertyValues.forEach((i: InterestDto) => {
          console.log('Name ' + i.name);
          this.interestNames.push(i.name);
          this.selectedInterestIds.push(i.id);
          console.log('this.selectedInterestIds ' + this.selectedInterestIds);
        });
      }
    });
  }

  getInterests() {
    this.interestService
      .getAllInterests()
      .subscribe((interests: Array<InterestDto>) => {
        this.allInterests = interests;
        console.log('All interests', interests);
      });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 5000,
    });
  }

  clearData(){
    this.registerForm.reset();
    this.interestNames = [];
    this.selectedInterestIds = [];
  }

  registerUser() {
    console.log('registerForm', this.registerForm);
    let userModel = new UserModel(
      this.registerForm.value.firstName,
      this.registerForm.value.lastName,
      this.registerForm.value.emailAddress
    );

    userModel.setSubscribedInterests(this.selectedInterestIds);
    this.userService.registerUser(userModel).subscribe((resp: string) => {
      console.log(resp);
      this.clearData();
      this.openSnackBar(
        'Registration successful. You are now subscribed to our weekly feeds. ',
        'SUCCESS!!'
      );
    });
  }
}
