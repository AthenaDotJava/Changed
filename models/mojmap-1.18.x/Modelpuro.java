// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelpuro<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "puro"), "main");
	private final ModelPart head;
	private final ModelPart Ears;
	private final ModelPart headwear;
	private final ModelPart body;
	private final ModelPart jacket;
	private final ModelPart left_arm;
	private final ModelPart left_sleve;
	private final ModelPart right_arm;
	private final ModelPart right_sleve;
	private final ModelPart left_leg;
	private final ModelPart left_pants;
	private final ModelPart right_leg;
	private final ModelPart right_pants;
	private final ModelPart tail;

	public Modelpuro(ModelPart root) {
		this.head = root.getChild("head");
		this.Ears = root.getChild("Ears");
		this.headwear = root.getChild("headwear");
		this.body = root.getChild("body");
		this.jacket = root.getChild("jacket");
		this.left_arm = root.getChild("left_arm");
		this.left_sleve = root.getChild("left_sleve");
		this.right_arm = root.getChild("right_arm");
		this.right_sleve = root.getChild("right_sleve");
		this.left_leg = root.getChild("left_leg");
		this.left_pants = root.getChild("left_pants");
		this.right_leg = root.getChild("right_leg");
		this.right_pants = root.getChild("right_pants");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(
				-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Ears = partdefinition.addOrReplaceChild("Ears", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right = Ears.addOrReplaceChild("right",
				CubeListBuilder.create().texOffs(0, 65)
						.addBox(0.0F, -1.0F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 80)
						.addBox(-1.0F, -3.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(6, 73)
						.addBox(-1.0F, -4.0F, 1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(1, 75)
						.addBox(0.0F, -5.0F, 1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(13, 67)
						.addBox(0.0F, -4.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(8, 73)
						.addBox(-1.0F, -2.0F, -2.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, -7.0F, 2.0F));

		PartDefinition left = Ears.addOrReplaceChild("left",
				CubeListBuilder.create().texOffs(24, 66)
						.addBox(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(32, 74)
						.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(24, 81)
						.addBox(-1.0F, -3.0F, 0.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(51, 71)
						.addBox(-1.0F, -4.0F, 1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(25, 76)
						.addBox(-1.0F, -5.0F, 2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(30, 74)
						.addBox(0.0F, -4.0F, 2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-4.0F, -7.0F, 1.0F));

		PartDefinition headwear = partdefinition.addOrReplaceChild("headwear",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)).texOffs(71, 0)
						.addBox(-2.0F, -3.0F, -7.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-1.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(
				-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition jacket = partdefinition.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(28, 28)
				.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(56, 0)
				.addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition left_sleve = partdefinition.addOrReplaceChild("left_sleve", CubeListBuilder.create()
				.texOffs(52, 44).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
				.texOffs(52, 28).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition right_sleve = partdefinition.addOrReplaceChild("right_sleve", CubeListBuilder.create()
				.texOffs(0, 48).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(36, 44)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition left_pants = partdefinition.addOrReplaceChild("left_pants", CubeListBuilder.create()
				.texOffs(20, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
				.texOffs(44, 12).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition right_pants = partdefinition.addOrReplaceChild("right_pants", CubeListBuilder.create()
				.texOffs(32, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0.0F, 9.0F, 1.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition tail1 = tail.addOrReplaceChild("tail1", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = tail1.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(72, 10).addBox(-1.0F, 3.0F, -7.0F, 2.0F, 2.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.393F, 8.2533F, -0.3491F, 0.0F, 0.0F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create(),
				PartPose.offset(0.0F, 1.607F, 2.2533F));

		PartDefinition cube_r2 = tail2.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(73, 21).addBox(-2.0F, 2.5F, -5.0F, 4.0F, 4.0F, 3.0F,
						new CubeDeformation(-0.5F)),
				PartPose.offsetAndRotation(0.0F, -3.0F, 6.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition cube_r3 = tail3.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(72, 31).addBox(-3.0F, 1.25F, -4.0F, 6.0F, 6.0F, 3.0F,
						new CubeDeformation(-0.5F)),
				PartPose.offsetAndRotation(0.0F, -3.0F, 5.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create(),
				PartPose.offset(0.0F, 1.0F, 2.0F));

		PartDefinition cube_r4 = tail4.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(89, 1).addBox(-3.0F, 6.2322F, -4.8022F, 6.0F, 6.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -7.0F, 8.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition tail5 = tail4.addOrReplaceChild("tail5", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition cube_r5 = tail5.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(94, 38).addBox(-4.0F, 4.969F, -4.2258F, 8.0F, 8.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -7.0F, 7.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition tail6 = tail5.addOrReplaceChild("tail6", CubeListBuilder.create(),
				PartPose.offset(0.0F, 3.0F, 9.0F));

		PartDefinition cube_r6 = tail6.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(108, 21).addBox(-3.0F, 6.3372F, -6.0038F, 6.0F, 6.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -10.0F, -2.0F, -3.0107F, 0.0F, 3.1416F));

		PartDefinition tail7 = tail6.addOrReplaceChild("tail7", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition cube_r7 = tail7.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(85, 11).addBox(-3.0F, 6.3678F, -8.6019F, 6.0F, 6.0F, 5.0F,
						new CubeDeformation(-0.5F)),
				PartPose.offsetAndRotation(0.0F, -10.0F, -4.0F, -3.098F, 0.0F, 3.1416F));

		PartDefinition tail8 = tail7.addOrReplaceChild("tail8", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition cube_r8 = tail8.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(108, 10).addBox(-2.0F, 8.119F, -10.3676F, 4.0F, 4.0F, 4.0F,
						new CubeDeformation(-0.5F)),
				PartPose.offsetAndRotation(0.0F, -10.0F, -8.0F, 3.1416F, 0.0F, 3.1416F));

		PartDefinition tail9 = tail8.addOrReplaceChild("tail9", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition cube_r9 = tail9.addOrReplaceChild("cube_r9",
				CubeListBuilder.create().texOffs(117, 3).addBox(-1.0F, 9.1095F, 9.1117F, 2.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -10.0F, -10.0F, 0.0436F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Ears.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		headwear.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		jacket.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_sleve.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_sleve.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_pants.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_pants.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.right_arm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.left_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.tail.yRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.right_sleve.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.left_sleve.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.headwear.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.headwear.xRot = headPitch / (180F / (float) Math.PI);
		this.left_arm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.right_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.right_pants.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.Ears.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.Ears.xRot = headPitch / (180F / (float) Math.PI);
		this.left_pants.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
	}
}