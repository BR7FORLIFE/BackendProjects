import type { IAuthRepositoryPort } from '../../application/ports/auth-repository.port.js';
import { PrismaConfig } from '../../../config/db.config.js';
import { User } from '../../../core/models/db.models.js';
import { Injectable } from '@nestjs/common';

@Injectable()
export class AuthRepository implements IAuthRepositoryPort {
  constructor(private readonly prisma: PrismaConfig) {}

  async findByEmail(email: string): Promise<User | null> {
    return this.prisma.user.findUnique({
      where: {
        email,
      },
    });
  }

  async save(user: User): Promise<{ id: string; username: string }> {
    const createUser = await this.prisma.user.create({
      data: {
        ...user,
      },
    });

    return {
      id: createUser.id,
      username: createUser.username,
    };
  }
}
