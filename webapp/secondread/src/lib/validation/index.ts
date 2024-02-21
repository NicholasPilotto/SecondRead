import * as z from 'zod';

export const LoginValidationSchema = z.object({
  email: z.string().email(),
  password: z.string(),
});

export const RegistrationValidationSchema = z.object({
  firstName: z.string().min(2, { message: 'Name is too short'}),
  lastName: z.string().min(2, { message: 'Last is too short'}),
  email: z.string().email(),
  password: z.string().min(8, {
    message: 'Password must be at least 8 characters'
  }),
});
