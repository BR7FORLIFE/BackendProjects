export interface IAuthRepositoryPort {
  findByEmail(email: string): Promise<void>;
}
