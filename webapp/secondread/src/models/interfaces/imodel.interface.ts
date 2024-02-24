export abstract class  IModel<T> {
  public id?: string;
  public createdAt?: Date;
  public updatedAt?: Date;

  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  public deserialize(input: any): IModel<T> {
    this.id = input?.id;
    this.createdAt = input?.createdAt;
    this.updatedAt = input?.updatedAt;

    return this;
  }

  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  public abstract toPayload(): any;
}
