// ==================================================================
// This file is part of Render Player API.
//
// Render Player API is free software: you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public License
// as published by the Free Software Foundation, either version 3 of
// the License, or (at your option) any later version.
//
// Render Player API is distributed in the hope that it will be
// useful, but WITHOUT ANY WARRANTY; without even the implied
// warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
// See the GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License and the GNU General Public License along with Render
// Player API. If not, see <http://www.gnu.org/licenses/>.
// ==================================================================

package api.player.model;

import java.io.*;

import org.objectweb.asm.*;

public final class ModelPlayerClassVisitor extends ClassVisitor
{
	public static final String targetClassName = "net.minecraft.client.model.ModelPlayer";
	public static final String obfuscatedClassReference = "bjx";
	public static final String obfuscatedSuperClassReference = "bjp";
	public static final String deobfuscatedClassReference = "net/minecraft/client/model/ModelPlayer";
	public static final String deobfuscateSuperClassReference = "net/minecraft/client/model/ModelBiped";

	private boolean hadLocalGetArmForSide;
	private boolean hadLocalGetMainHand;
	private boolean hadLocalGetRandomModelBox;
	private boolean hadLocalGetTextureOffset;
	private boolean hadLocalPostRenderArm;
	private boolean hadLocalRender;
	private boolean hadLocalRenderCape;
	private boolean hadLocalRenderDeadmau5Head;
	private boolean hadLocalSetInvisible;
	private boolean hadLocalSetLivingAnimations;
	private boolean hadLocalSetModelAttributes;
	private boolean hadLocalSetRotationAngles;
	private boolean hadLocalSetTextureOffset;

	public static byte[] transform(byte[] bytes, boolean isObfuscated)
	{
		try
		{
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			ClassReader cr = new ClassReader(in);
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			ModelPlayerClassVisitor p = new ModelPlayerClassVisitor(cw, isObfuscated);

			cr.accept(p, 0);

			byte[] result = cw.toByteArray();
			in.close();
			return result;
		}
		catch(IOException ioe)
		{
			throw new RuntimeException(ioe);
		}
	}

	private final boolean isObfuscated;

	public ModelPlayerClassVisitor(ClassVisitor classVisitor, boolean isObfuscated)
	{
		super(262144, classVisitor);
		this.isObfuscated = isObfuscated;
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
	{
		String[] newInterfaces = new String[interfaces.length + 1];
		for(int i=0; i<interfaces.length; i++)
			newInterfaces[i] = interfaces[i];
		newInterfaces[interfaces.length] = "api/player/model/IModelPlayerAPI";
		super.visit(version, access, name, signature, superName, newInterfaces);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
	{
		if(name.equals("<init>") && desc.equals("(FZ)V"))
		{
			desc = "(FZLjava/lang/String;)V";

			MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "(FZ)V", signature, exceptions);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.FLOAD, 1);
			mv.visitVarInsn(Opcodes.ILOAD, 2);
			mv.visitInsn(Opcodes.ACONST_NULL);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", "<init>", desc, false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();

			return new ModelPlayerConstructorVisitor(super.visitMethod(access, name, desc, signature, exceptions), isObfuscated, 3);
		}

		if(name.equals(isObfuscated ? "a" : "getArmForSide") && desc.equals(isObfuscated ? "(Lse;)Lblf;" : "(Lnet/minecraft/util/EnumHandSide;)Lnet/minecraft/client/model/ModelRenderer;"))
		{
			hadLocalGetArmForSide = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetArmForSide", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "getMainHand") && desc.equals(isObfuscated ? "(Lrw;)Lse;" : "(Lnet/minecraft/entity/Entity;)Lnet/minecraft/util/EnumHandSide;"))
		{
			hadLocalGetMainHand = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetMainHand", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "getRandomModelBox") && desc.equals(isObfuscated ? "(Ljava/util/Random;)Lblf;" : "(Ljava/util/Random;)Lnet/minecraft/client/model/ModelRenderer;"))
		{
			hadLocalGetRandomModelBox = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetRandomModelBox", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "getTextureOffset") && desc.equals(isObfuscated ? "(Ljava/lang/String;)Lblg;" : "(Ljava/lang/String;)Lnet/minecraft/client/model/TextureOffset;"))
		{
			hadLocalGetTextureOffset = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetTextureOffset", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "postRenderArm") && desc.equals(isObfuscated ? "(FLse;)V" : "(FLnet/minecraft/util/EnumHandSide;)V"))
		{
			hadLocalPostRenderArm = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localPostRenderArm", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "render") && desc.equals(isObfuscated ? "(Lrw;FFFFFF)V" : "(Lnet/minecraft/entity/Entity;FFFFFF)V"))
		{
			hadLocalRender = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRender", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "renderCape") && desc.equals("(F)V"))
		{
			hadLocalRenderCape = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderCape", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "renderDeadmau5Head") && desc.equals("(F)V"))
		{
			hadLocalRenderDeadmau5Head = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderDeadmau5Head", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "setInvisible") && desc.equals("(Z)V"))
		{
			hadLocalSetInvisible = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetInvisible", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "setLivingAnimations") && desc.equals(isObfuscated ? "(Lsf;FFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFF)V"))
		{
			hadLocalSetLivingAnimations = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetLivingAnimations", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "setModelAttributes") && desc.equals(isObfuscated ? "(Lbju;)V" : "(Lnet/minecraft/client/model/ModelBase;)V"))
		{
			hadLocalSetModelAttributes = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetModelAttributes", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "setRotationAngles") && desc.equals(isObfuscated ? "(FFFFFFLrw;)V" : "(FFFFFFLnet/minecraft/entity/Entity;)V"))
		{
			hadLocalSetRotationAngles = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetRotationAngles", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "setTextureOffset") && desc.equals("(Ljava/lang/String;II)V"))
		{
			hadLocalSetTextureOffset = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetTextureOffset", desc, signature, exceptions);
		}

		return super.visitMethod(access, name, desc, signature, exceptions);
	}

	@Override
	public void visitEnd()
	{
		MethodVisitor mv;

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "getArmForSide", "" + (isObfuscated ? "(Lse;)Lblf;" : "(Lnet/minecraft/util/EnumHandSide;)Lnet/minecraft/client/model/ModelRenderer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "getArmForSide", "(Lapi/player/model/IModelPlayerAPI;Lnet/minecraft/util/EnumHandSide;)" + (isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetArmForSide", "(Lnet/minecraft/util/EnumHandSide;)" + (isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "a" : "getArmForSide", "" + (isObfuscated ? "(Lse;)Lblf;" : "(Lnet/minecraft/util/EnumHandSide;)Lnet/minecraft/client/model/ModelRenderer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetArmForSide", "(Lnet/minecraft/util/EnumHandSide;)" + (isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "getArmForSide", "" + (isObfuscated ? "(Lse;)Lblf;" : "(Lnet/minecraft/util/EnumHandSide;)Lnet/minecraft/client/model/ModelRenderer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetArmForSide)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetArmForSide", "(Lnet/minecraft/util/EnumHandSide;)" + (isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "getArmForSide", "" + (isObfuscated ? "(Lse;)Lblf;" : "(Lnet/minecraft/util/EnumHandSide;)Lnet/minecraft/client/model/ModelRenderer;") + "", false);
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "getMainHand", "" + (isObfuscated ? "(Lrw;)Lse;" : "(Lnet/minecraft/entity/Entity;)Lnet/minecraft/util/EnumHandSide;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "getMainHand", "(Lapi/player/model/IModelPlayerAPI;Lnet/minecraft/entity/Entity;)" + (isObfuscated ? "Lse;" : "Lnet/minecraft/util/EnumHandSide;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetMainHand", "(Lnet/minecraft/entity/Entity;)" + (isObfuscated ? "Lse;" : "Lnet/minecraft/util/EnumHandSide;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "a" : "getMainHand", "" + (isObfuscated ? "(Lrw;)Lse;" : "(Lnet/minecraft/entity/Entity;)Lnet/minecraft/util/EnumHandSide;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetMainHand", "(Lnet/minecraft/entity/Entity;)" + (isObfuscated ? "Lse;" : "Lnet/minecraft/util/EnumHandSide;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "getMainHand", "" + (isObfuscated ? "(Lrw;)Lse;" : "(Lnet/minecraft/entity/Entity;)Lnet/minecraft/util/EnumHandSide;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetMainHand)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetMainHand", "(Lnet/minecraft/entity/Entity;)" + (isObfuscated ? "Lse;" : "Lnet/minecraft/util/EnumHandSide;") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "getMainHand", "" + (isObfuscated ? "(Lrw;)Lse;" : "(Lnet/minecraft/entity/Entity;)Lnet/minecraft/util/EnumHandSide;") + "", false);
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "getRandomModelBox", "" + (isObfuscated ? "(Ljava/util/Random;)Lblf;" : "(Ljava/util/Random;)Lnet/minecraft/client/model/ModelRenderer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "getRandomModelBox", "(Lapi/player/model/IModelPlayerAPI;Ljava/util/Random;)" + (isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetRandomModelBox", "(Ljava/util/Random;)" + (isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "a" : "getRandomModelBox", "" + (isObfuscated ? "(Ljava/util/Random;)Lblf;" : "(Ljava/util/Random;)Lnet/minecraft/client/model/ModelRenderer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetRandomModelBox", "(Ljava/util/Random;)" + (isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "getRandomModelBox", "" + (isObfuscated ? "(Ljava/util/Random;)Lblf;" : "(Ljava/util/Random;)Lnet/minecraft/client/model/ModelRenderer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetRandomModelBox)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetRandomModelBox", "(Ljava/util/Random;)" + (isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "getRandomModelBox", "" + (isObfuscated ? "(Ljava/util/Random;)Lblf;" : "(Ljava/util/Random;)Lnet/minecraft/client/model/ModelRenderer;") + "", false);
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "getTextureOffset", "" + (isObfuscated ? "(Ljava/lang/String;)Lblg;" : "(Ljava/lang/String;)Lnet/minecraft/client/model/TextureOffset;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "getTextureOffset", "(Lapi/player/model/IModelPlayerAPI;Ljava/lang/String;)" + (isObfuscated ? "Lblg;" : "Lnet/minecraft/client/model/TextureOffset;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetTextureOffset", "(Ljava/lang/String;)" + (isObfuscated ? "Lblg;" : "Lnet/minecraft/client/model/TextureOffset;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "a" : "getTextureOffset", "" + (isObfuscated ? "(Ljava/lang/String;)Lblg;" : "(Ljava/lang/String;)Lnet/minecraft/client/model/TextureOffset;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetTextureOffset", "(Ljava/lang/String;)" + (isObfuscated ? "Lblg;" : "Lnet/minecraft/client/model/TextureOffset;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "getTextureOffset", "" + (isObfuscated ? "(Ljava/lang/String;)Lblg;" : "(Ljava/lang/String;)Lnet/minecraft/client/model/TextureOffset;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetTextureOffset)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetTextureOffset", "(Ljava/lang/String;)" + (isObfuscated ? "Lblg;" : "Lnet/minecraft/client/model/TextureOffset;") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "getTextureOffset", "" + (isObfuscated ? "(Ljava/lang/String;)Lblg;" : "(Ljava/lang/String;)Lnet/minecraft/client/model/TextureOffset;") + "", false);
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "postRenderArm", "" + (isObfuscated ? "(FLse;)V" : "(FLnet/minecraft/util/EnumHandSide;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "postRenderArm", "(Lapi/player/model/IModelPlayerAPI;FLnet/minecraft/util/EnumHandSide;)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realPostRenderArm", "(FLnet/minecraft/util/EnumHandSide;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "a" : "postRenderArm", "" + (isObfuscated ? "(FLse;)V" : "(FLnet/minecraft/util/EnumHandSide;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superPostRenderArm", "(FLnet/minecraft/util/EnumHandSide;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "postRenderArm", "" + (isObfuscated ? "(FLse;)V" : "(FLnet/minecraft/util/EnumHandSide;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalPostRenderArm)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localPostRenderArm", "(FLnet/minecraft/util/EnumHandSide;)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.FLOAD, 1);
			mv.visitVarInsn(Opcodes.ALOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "postRenderArm", "" + (isObfuscated ? "(FLse;)V" : "(FLnet/minecraft/util/EnumHandSide;)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "render", "" + (isObfuscated ? "(Lrw;FFFFFF)V" : "(Lnet/minecraft/entity/Entity;FFFFFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "render", "(Lapi/player/model/IModelPlayerAPI;Lnet/minecraft/entity/Entity;FFFFFF)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRender", "(Lnet/minecraft/entity/Entity;FFFFFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "a" : "render", "" + (isObfuscated ? "(Lrw;FFFFFF)V" : "(Lnet/minecraft/entity/Entity;FFFFFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRender", "(Lnet/minecraft/entity/Entity;FFFFFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "render", "" + (isObfuscated ? "(Lrw;FFFFFF)V" : "(Lnet/minecraft/entity/Entity;FFFFFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRender)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRender", "(Lnet/minecraft/entity/Entity;FFFFFF)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitVarInsn(Opcodes.FLOAD, 4);
			mv.visitVarInsn(Opcodes.FLOAD, 5);
			mv.visitVarInsn(Opcodes.FLOAD, 6);
			mv.visitVarInsn(Opcodes.FLOAD, 7);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "render", "" + (isObfuscated ? "(Lrw;FFFFFF)V" : "(Lnet/minecraft/entity/Entity;FFFFFF)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "renderCape", "(F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "renderCape", "(Lapi/player/model/IModelPlayerAPI;F)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderCape", "(F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "b" : "renderCape", "(F)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderCape", "(F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "b" : "renderCape", "(F)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderCape)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderCape", "(F)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.FLOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "b" : "renderCape", "(F)V", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderDeadmau5Head", "(F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "renderDeadmau5Head", "(Lapi/player/model/IModelPlayerAPI;F)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderDeadmau5Head", "(F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "a" : "renderDeadmau5Head", "(F)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderDeadmau5Head", "(F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "renderDeadmau5Head", "(F)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderDeadmau5Head)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderDeadmau5Head", "(F)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.FLOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "renderDeadmau5Head", "(F)V", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "setInvisible", "(Z)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "setInvisible", "(Lapi/player/model/IModelPlayerAPI;Z)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetInvisible", "(Z)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "a" : "setInvisible", "(Z)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetInvisible", "(Z)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "setInvisible", "(Z)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetInvisible)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetInvisible", "(Z)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ILOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "setInvisible", "(Z)V", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "setLivingAnimations", "" + (isObfuscated ? "(Lsf;FFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "setLivingAnimations", "(Lapi/player/model/IModelPlayerAPI;Lnet/minecraft/entity/EntityLivingBase;FFF)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetLivingAnimations", "(Lnet/minecraft/entity/EntityLivingBase;FFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "a" : "setLivingAnimations", "" + (isObfuscated ? "(Lsf;FFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetLivingAnimations", "(Lnet/minecraft/entity/EntityLivingBase;FFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "setLivingAnimations", "" + (isObfuscated ? "(Lsf;FFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetLivingAnimations)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetLivingAnimations", "(Lnet/minecraft/entity/EntityLivingBase;FFF)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitVarInsn(Opcodes.FLOAD, 4);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "setLivingAnimations", "" + (isObfuscated ? "(Lsf;FFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFF)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "setModelAttributes", "" + (isObfuscated ? "(Lbju;)V" : "(Lnet/minecraft/client/model/ModelBase;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "setModelAttributes", "(Lapi/player/model/IModelPlayerAPI;Lnet/minecraft/client/model/ModelBase;)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetModelAttributes", "(Lnet/minecraft/client/model/ModelBase;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "a" : "setModelAttributes", "" + (isObfuscated ? "(Lbju;)V" : "(Lnet/minecraft/client/model/ModelBase;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetModelAttributes", "(Lnet/minecraft/client/model/ModelBase;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "setModelAttributes", "" + (isObfuscated ? "(Lbju;)V" : "(Lnet/minecraft/client/model/ModelBase;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetModelAttributes)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetModelAttributes", "(Lnet/minecraft/client/model/ModelBase;)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "setModelAttributes", "" + (isObfuscated ? "(Lbju;)V" : "(Lnet/minecraft/client/model/ModelBase;)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "setRotationAngles", "" + (isObfuscated ? "(FFFFFFLrw;)V" : "(FFFFFFLnet/minecraft/entity/Entity;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.ALOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "setRotationAngles", "(Lapi/player/model/IModelPlayerAPI;FFFFFFLnet/minecraft/entity/Entity;)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetRotationAngles", "(FFFFFFLnet/minecraft/entity/Entity;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.ALOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "a" : "setRotationAngles", "" + (isObfuscated ? "(FFFFFFLrw;)V" : "(FFFFFFLnet/minecraft/entity/Entity;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetRotationAngles", "(FFFFFFLnet/minecraft/entity/Entity;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.ALOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "setRotationAngles", "" + (isObfuscated ? "(FFFFFFLrw;)V" : "(FFFFFFLnet/minecraft/entity/Entity;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetRotationAngles)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetRotationAngles", "(FFFFFFLnet/minecraft/entity/Entity;)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.FLOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitVarInsn(Opcodes.FLOAD, 4);
			mv.visitVarInsn(Opcodes.FLOAD, 5);
			mv.visitVarInsn(Opcodes.FLOAD, 6);
			mv.visitVarInsn(Opcodes.ALOAD, 7);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "setRotationAngles", "" + (isObfuscated ? "(FFFFFFLrw;)V" : "(FFFFFFLnet/minecraft/entity/Entity;)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "setTextureOffset", "(Ljava/lang/String;II)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.ILOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "setTextureOffset", "(Lapi/player/model/IModelPlayerAPI;Ljava/lang/String;II)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetTextureOffset", "(Ljava/lang/String;II)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.ILOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "a" : "setTextureOffset", "(Ljava/lang/String;II)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetTextureOffset", "(Ljava/lang/String;II)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.ILOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "setTextureOffset", "(Ljava/lang/String;II)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetTextureOffset)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetTextureOffset", "(Ljava/lang/String;II)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.ILOAD, 2);
			mv.visitVarInsn(Opcodes.ILOAD, 3);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjp" : "net/minecraft/client/model/ModelBiped", isObfuscated ? "a" : "setTextureOffset", "(Ljava/lang/String;II)V", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBipedBodyField", isObfuscated ? "()Lblf;" : "()Lnet/minecraft/client/model/ModelRenderer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "g" : "bipedBody", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setBipedBodyField", isObfuscated ? "(Lblf;)V" : "(Lnet/minecraft/client/model/ModelRenderer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "g" : "bipedBody", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBipedBodyWearField", isObfuscated ? "()Lblf;" : "()Lnet/minecraft/client/model/ModelRenderer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "u" : "bipedBodyWear", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setBipedBodyWearField", isObfuscated ? "(Lblf;)V" : "(Lnet/minecraft/client/model/ModelRenderer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "u" : "bipedBodyWear", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBipedCapeField", isObfuscated ? "()Lblf;" : "()Lnet/minecraft/client/model/ModelRenderer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "v" : "bipedCape", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBipedDeadmau5HeadField", isObfuscated ? "()Lblf;" : "()Lnet/minecraft/client/model/ModelRenderer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "w" : "bipedDeadmau5Head", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBipedHeadField", isObfuscated ? "()Lblf;" : "()Lnet/minecraft/client/model/ModelRenderer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "e" : "bipedHead", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setBipedHeadField", isObfuscated ? "(Lblf;)V" : "(Lnet/minecraft/client/model/ModelRenderer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "e" : "bipedHead", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBipedHeadwearField", isObfuscated ? "()Lblf;" : "()Lnet/minecraft/client/model/ModelRenderer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "f" : "bipedHeadwear", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setBipedHeadwearField", isObfuscated ? "(Lblf;)V" : "(Lnet/minecraft/client/model/ModelRenderer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "f" : "bipedHeadwear", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBipedLeftArmField", isObfuscated ? "()Lblf;" : "()Lnet/minecraft/client/model/ModelRenderer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "i" : "bipedLeftArm", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setBipedLeftArmField", isObfuscated ? "(Lblf;)V" : "(Lnet/minecraft/client/model/ModelRenderer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "i" : "bipedLeftArm", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBipedLeftArmwearField", isObfuscated ? "()Lblf;" : "()Lnet/minecraft/client/model/ModelRenderer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "a" : "bipedLeftArmwear", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setBipedLeftArmwearField", isObfuscated ? "(Lblf;)V" : "(Lnet/minecraft/client/model/ModelRenderer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "a" : "bipedLeftArmwear", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBipedLeftLegField", isObfuscated ? "()Lblf;" : "()Lnet/minecraft/client/model/ModelRenderer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "k" : "bipedLeftLeg", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setBipedLeftLegField", isObfuscated ? "(Lblf;)V" : "(Lnet/minecraft/client/model/ModelRenderer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "k" : "bipedLeftLeg", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBipedLeftLegwearField", isObfuscated ? "()Lblf;" : "()Lnet/minecraft/client/model/ModelRenderer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "c" : "bipedLeftLegwear", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setBipedLeftLegwearField", isObfuscated ? "(Lblf;)V" : "(Lnet/minecraft/client/model/ModelRenderer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "c" : "bipedLeftLegwear", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBipedRightArmField", isObfuscated ? "()Lblf;" : "()Lnet/minecraft/client/model/ModelRenderer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "h" : "bipedRightArm", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setBipedRightArmField", isObfuscated ? "(Lblf;)V" : "(Lnet/minecraft/client/model/ModelRenderer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "h" : "bipedRightArm", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBipedRightArmwearField", isObfuscated ? "()Lblf;" : "()Lnet/minecraft/client/model/ModelRenderer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "b" : "bipedRightArmwear", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setBipedRightArmwearField", isObfuscated ? "(Lblf;)V" : "(Lnet/minecraft/client/model/ModelRenderer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "b" : "bipedRightArmwear", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBipedRightLegField", isObfuscated ? "()Lblf;" : "()Lnet/minecraft/client/model/ModelRenderer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "j" : "bipedRightLeg", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setBipedRightLegField", isObfuscated ? "(Lblf;)V" : "(Lnet/minecraft/client/model/ModelRenderer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "j" : "bipedRightLeg", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBipedRightLegwearField", isObfuscated ? "()Lblf;" : "()Lnet/minecraft/client/model/ModelRenderer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "d" : "bipedRightLegwear", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setBipedRightLegwearField", isObfuscated ? "(Lblf;)V" : "(Lnet/minecraft/client/model/ModelRenderer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "d" : "bipedRightLegwear", isObfuscated ? "Lblf;" : "Lnet/minecraft/client/model/ModelRenderer;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBoxListField", "()Ljava/util/List;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "r" : "boxList", "Ljava/util/List;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setBoxListField", "(Ljava/util/List;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "r" : "boxList", "Ljava/util/List;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getIsChildField", "()Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "q" : "isChild", "Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setIsChildField", "(Z)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "q" : "isChild", "Z");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getIsRidingField", "()Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "p" : "isRiding", "Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setIsRidingField", "(Z)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "p" : "isRiding", "Z");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getIsSneakField", "()Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "n" : "isSneak", "Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setIsSneakField", "(Z)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "n" : "isSneak", "Z");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getLeftArmPoseField", isObfuscated ? "()Lbjp$a;" : "()Lnet/minecraft/client/model/ModelBiped$ArmPose;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "l" : "leftArmPose", isObfuscated ? "Lbjp$a;" : "Lnet/minecraft/client/model/ModelBiped$ArmPose;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setLeftArmPoseField", isObfuscated ? "(Lbjp$a;)V" : "(Lnet/minecraft/client/model/ModelBiped$ArmPose;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "l" : "leftArmPose", isObfuscated ? "Lbjp$a;" : "Lnet/minecraft/client/model/ModelBiped$ArmPose;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRightArmPoseField", isObfuscated ? "()Lbjp$a;" : "()Lnet/minecraft/client/model/ModelBiped$ArmPose;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "m" : "rightArmPose", isObfuscated ? "Lbjp$a;" : "Lnet/minecraft/client/model/ModelBiped$ArmPose;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRightArmPoseField", isObfuscated ? "(Lbjp$a;)V" : "(Lnet/minecraft/client/model/ModelBiped$ArmPose;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "m" : "rightArmPose", isObfuscated ? "Lbjp$a;" : "Lnet/minecraft/client/model/ModelBiped$ArmPose;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getSmallArmsField", "()Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "x" : "smallArms", "Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getSwingProgressField", "()F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "o" : "swingProgress", "F");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setSwingProgressField", "(F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "o" : "swingProgress", "F");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getTextureHeightField", "()I", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "t" : "textureHeight", "I");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setTextureHeightField", "(I)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "t" : "textureHeight", "I");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getTextureWidthField", "()I", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "s" : "textureWidth", "I");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setTextureWidthField", "(I)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", isObfuscated ? "s" : "textureWidth", "I");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getModelPlayerBase", "(Ljava/lang/String;)Lapi/player/model/ModelPlayerBase;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "getModelPlayerBase", "(Lapi/player/model/IModelPlayerAPI;Ljava/lang/String;)Lapi/player/model/ModelPlayerBase;", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getModelPlayerBaseIds", "()Ljava/util/Set;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "getModelPlayerBaseIds", "(Lapi/player/model/IModelPlayerAPI;)Ljava/util/Set;", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getExpandParameter", "()F", null, null);
		mv.visitVarInsn(/*Opcodes*/25/*ALOAD*/, /*0*/0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "getExpandParameter", "(Lapi/player/model/IModelPlayerAPI;)F", false);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getYOffsetParameter", "()F", null, null);
		mv.visitVarInsn(/*Opcodes*/25/*ALOAD*/, /*0*/0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "getYOffsetParameter", "(Lapi/player/model/IModelPlayerAPI;)F", false);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getTextureWidthParameter", "()I", null, null);
		mv.visitVarInsn(/*Opcodes*/25/*ALOAD*/, /*0*/0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "getTextureWidthParameter", "(Lapi/player/model/IModelPlayerAPI;)I", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getTextureHeightParameter", "()I", null, null);
		mv.visitVarInsn(/*Opcodes*/25/*ALOAD*/, /*0*/0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "getTextureHeightParameter", "(Lapi/player/model/IModelPlayerAPI;)I", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getSmallArmsParameter", "()Z", null, null);
		mv.visitVarInsn(/*Opcodes*/25/*ALOAD*/, /*0*/0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "getSmallArmsParameter", "(Lapi/player/model/IModelPlayerAPI;)Z", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 1);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getModelPlayerType", "()Ljava/lang/String;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "getModelPlayerType", "(Lapi/player/model/IModelPlayerAPI;)Ljava/lang/String;", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "dynamic", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "dynamic", "(Lapi/player/model/IModelPlayerAPI;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getModelPlayerAPI", "()Lapi/player/model/ModelPlayerAPI;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bjx" : "net/minecraft/client/model/ModelPlayer", "modelPlayerAPI", "Lapi/player/model/ModelPlayerAPI;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getModelPlayer", isObfuscated ? "()Lbjp;" : "()Lnet/minecraft/client/model/ModelBiped;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "getAllInstances", isObfuscated ? "()[Lbjp;" : "()[Lnet/minecraft/client/model/ModelBiped;", null, null);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "getAllInstances", isObfuscated ? "()[Lbjp;" : "()[Lnet/minecraft/client/model/ModelBiped;", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		cv.visitField(Opcodes.ACC_PRIVATE | Opcodes.ACC_FINAL, "modelPlayerAPI", "Lapi/player/model/ModelPlayerAPI;", null, null);
	}
}
