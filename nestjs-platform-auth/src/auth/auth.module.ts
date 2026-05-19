import { Module } from '@nestjs/common';

import { AuthUseCaseImp } from './application/orchestator/auth.service';
import { AuthController } from './infra/controllers/auth.controller';
import { AuthRepository } from './infra/repository/auth.repository';

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
  ],
})
export class AuthModule {}
