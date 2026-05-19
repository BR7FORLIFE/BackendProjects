import { Module } from '@nestjs/common';

import { AuthUseCaseImp } from './application/orchestator/auth.service.js';
import { AuthController } from './infra/controllers/auth.controller.js';
import { AuthRepository } from './infra/repository/auth.repository.js';

import { PrismaConfig } from '../config/db.config.js';

@Module({
  controllers: [AuthController],
  providers: [
    {
      provide: 'AUTH_USECASE',
      useClass: AuthUseCaseImp,
    },
    {
      provide: 'AUTH_REPOSITORY',
      useClass: AuthRepository,
    },
    PrismaConfig,
  ],
})
export class AuthModule {}
