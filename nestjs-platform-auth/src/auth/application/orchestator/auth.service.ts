import { Inject, Injectable } from '@nestjs/common';
import * as crypto from 'crypto';
import * as bcryp from 'bcrypt';

import {
  OAUTH2_GOOGLE_PATH,
  OAUTH2_GOOGLE_CLIENT_ID,
  // OAUTH2_GOOGLE_CLIENT_SECRET,
  OAUTH2_GOOGLE_REDIRECT_URL,
} from '../../../constants.js';
import type { AuthUseCase } from '../usecases/auth.usecase.js';
import type { IAuthRepositoryPort } from '../ports/auth-repository.port.js';
import type { User } from '../../../core/models/db.models.js';
import type { registerUserDto } from '../dtos/register-user.dto.js';
import { UserAlreadyExistsException } from '../exceptions/user-alreadt-exists.js';

@Injectable()
export class AuthUseCaseImp implements AuthUseCase {
  constructor(
    @Inject('AUTH_REPOSITORY') private repository: IAuthRepositoryPort,
  ) {}

  async register(
    register: registerUserDto,
  ): Promise<{ id: string; username: string }> {
    const user: User | null = await this.repository.findByEmail(register.email);

    if (user) {
      throw new UserAlreadyExistsException();
    }

    const passwordHash = await bcryp.hash(register.password, 10);

    const newUser: User = {
      username: register.username,
      email: register.email,
      password: passwordHash,
      createAt: new Date(),
    };

    const data = await this.repository.save(newUser);

    return { ...data };
  }

  initializerOAuth2() {
    const state = crypto.randomBytes(20).toString('hex');

    const params = new URLSearchParams({
      client_id: OAUTH2_GOOGLE_CLIENT_ID as string,
      redirect_uri: OAUTH2_GOOGLE_REDIRECT_URL as string,
      response_type: 'code',
      scope: 'openid profile email',
      state,
      access_type: 'offline',
      prompt: 'consent',
    });

    const redirectUrl = `${OAUTH2_GOOGLE_PATH}?${params.toString()}`;
    // state para mandarlo en un cookie y ver el estado del auth
    //redirect url seria la url de oath2 google para poder autenticar al usuario
    return { redirectUrl, state };
  }

  handleOauth2Params(): { access_token: string } {
    return { access_token: '' };
  }
}
