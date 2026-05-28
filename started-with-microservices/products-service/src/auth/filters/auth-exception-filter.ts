import {
  Catch,
  ExceptionFilter,
  HttpStatus,
  type ArgumentsHost,
} from '@nestjs/common';
import { UserNotFound } from '../exceptions/user-not-found.js';
import { Request, Response } from 'express';

//los filters en nestjs con manejadores de excepciones no middlewares
@Catch(UserNotFound)
export class AuthExceptionFilter implements ExceptionFilter {
  catch(exception: UserNotFound, host: ArgumentsHost) {
    const ctx = host.switchToHttp();

    const req = ctx.getRequest<Request>();
    const res = ctx.getResponse<Response>();

    return res.status(HttpStatus.NOT_FOUND).json({
      path: req.path,
      cause: exception.message,
      timestamp: new Date().toISOString(),
      status: 404,
    });
  }
}
