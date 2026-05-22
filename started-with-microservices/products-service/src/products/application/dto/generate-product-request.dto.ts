import { IsNotEmpty, IsPositive, IsString } from 'class-validator';

export class GenerateProductRequest {
  @IsNotEmpty()
  @IsString()
  sku: string;

  @IsString()
  @IsNotEmpty()
  name: string;

  @IsPositive()
  @IsNotEmpty()
  quantity: number;
}
