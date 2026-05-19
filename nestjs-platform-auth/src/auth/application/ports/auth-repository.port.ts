import { User } from '../../../core/models/db.models.js';

export interface IAuthRepositoryPort {
  findByEmail(email: string): Promise<User | null>;

  save(user: User): Promise<{ id: string; username: string }>;
}
