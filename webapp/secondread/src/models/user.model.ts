import { IModel } from "./interfaces/imodel.interface";

export class User extends IModel<User> {
  public firstName: string = '';
  public lastName: string = '';
  public birthDate: Date | null = null;
  public phoneNumber: string = '';
  public email: string = '';

  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  public deserialize(input: any): IModel<User> {
    this.firstName = input?.firstName;
    this.lastName = input?.lastName;
    this.birthDate = input?.birthDate;
    this.phoneNumber = input?.phoneNumber;
    this.email = input?.email;

    return this;
  }

  public toPayload() {
    return {
      firstName: this.firstName,
      lastName: this.lastName,
      birthDate: this.birthDate,
      phoneNumber: this.phoneNumber,
      email: this.email,
    };
  }
}
