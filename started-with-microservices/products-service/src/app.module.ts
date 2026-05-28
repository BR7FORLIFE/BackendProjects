import { Module } from '@nestjs/common';
import { AuthModule } from './auth/auth.module.js';
import { ProductModule } from './products/product.module.js';
import { PrismaClientConfig } from './config/prisma-client.js';

@Module({
  imports: [AuthModule, ProductModule],
  controllers: [],
  providers: [PrismaClientConfig],
})
export class AppModule {}
