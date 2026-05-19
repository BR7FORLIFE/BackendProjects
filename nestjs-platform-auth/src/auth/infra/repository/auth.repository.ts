import type { IAuthRepositoryPort } from '../../application/ports/auth-repository.port';

export class AuthRepository implements IAuthRepositoryPort {
  async findByEmail(email: string, password: string): Promise<void> {}
}
