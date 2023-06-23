export class UserModel {
  private firstName: string ='';
  private lastName: string ='';
  private emailAddress: string ='';
  private subscribedInterests:string[] = [];

  constructor(firstName:string, lastName:string, emailAddress:string){
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
  }

  setSubscribedInterests(interestIds:string[]){
    this.subscribedInterests = interestIds;
  }
}
