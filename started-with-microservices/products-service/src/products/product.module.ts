import { Module } from '@nestjs/common';
import { ProductsController } from './infra/controllers/product.controller.js';
import { ProductUseCaseImp } from './application/orchestator/product-orchestator.js';
import { ProductRepository } from './infra/repository/product.repository.js';
import { PrismaClientConfig } from '../config/prisma-client.js';

@Module({
  controllers: [ProductsController],
  providers: [ProductUseCaseImp, ProductRepository, PrismaClientConfig],
})
export class ProductModule {}
