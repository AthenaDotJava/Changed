// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelcustom_model<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "custom_model"), "main");
	private final ModelPart head;
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

	public Modelcustom_model(ModelPart root) {
		this.head = root.getChild("head");
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

		PartDefinition headwear = partdefinition.addOrReplaceChild("headwear", CubeListBuilder.create().texOffs(0, 0)
				.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ears = headwear.addOrReplaceChild("ears", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left = ears.addOrReplaceChild("left",
				CubeListBuilder.create().texOffs(54, 46)
						.addBox(-1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(24, 16)
						.addBox(-1.0F, -2.0F, -3.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(56, 35)
						.addBox(-1.0F, -3.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(62, 23)
						.addBox(-1.0F, -4.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 20)
						.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(58, 59)
						.addBox(-1.0F, -5.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 16)
						.addBox(-1.0F, -6.0F, 1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(3.0F, -8.0F, 4.0F));

		PartDefinition right = ears.addOrReplaceChild("right",
				CubeListBuilder.create().texOffs(75, 12)
						.addBox(-2.0F, -1.0F, -4.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(83, 0)
						.addBox(-2.0F, -2.0F, -3.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(93, 12)
						.addBox(-1.0F, -3.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(99, 0)
						.addBox(-1.0F, -4.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(72, 6)
						.addBox(-2.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(112, 3)
						.addBox(-1.0F, -5.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(72, 0)
						.addBox(-1.0F, -6.0F, 1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.0F, -8.0F, 4.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 0).addBox(
				-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition jacket = partdefinition.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(0, 32)
				.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(56, 0)
				.addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition left_sleve = partdefinition.addOrReplaceChild("left_sleve", CubeListBuilder.create()
				.texOffs(44, 52).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
				.texOffs(30, 52).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition right_sleve = partdefinition.addOrReplaceChild("right_sleve", CubeListBuilder.create()
				.texOffs(16, 52).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 48)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition left_pants = partdefinition.addOrReplaceChild("left_pants", CubeListBuilder.create()
				.texOffs(40, 36).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
				.texOffs(40, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition right_pants = partdefinition.addOrReplaceChild("right_pants", CubeListBuilder.create()
				.texOffs(24, 36).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create(),
				PartPose.offset(0.0F, 11.0F, 2.0F));

		PartDefinition main = tail.addOrReplaceChild("main", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = main
				.addOrReplaceChild("cube_r1",
						CubeListBuilder.create().texOffs(22, 22).addBox(-2.0F, -1.0F, 7.0F, 4.0F, 4.0F, 10.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r2 = main
				.addOrReplaceChild("cube_r2",
						CubeListBuilder.create().texOffs(50, 26).addBox(-1.5F, 1.5F, 16.0F, 3.0F, 3.0F, 6.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.384F, 0.0F, 0.0F));

		PartDefinition cube_r3 = main.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(22, 102)
						.addBox(-1.5F, -1.5F, 1.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(0, 4)
						.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6545F, 0.0F, 0.0F));

		PartDefinition middle_tail_fin = tail.addOrReplaceChild("middle_tail_fin", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r4 = middle_tail_fin.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(24, 0)
						.addBox(-0.5F, -5.0F, 13.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-0.5F, -4.0F, 12.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(52, 35)
						.addBox(-0.5F, -3.0F, 11.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(46, 71)
						.addBox(-0.5F, -2.0F, 10.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition end_tail_fin = tail.addOrReplaceChild("end_tail_fin", CubeListBuilder.create(),
				PartPose.offset(0.0F, 3.0F, -1.0F));

		PartDefinition cube_r5 = end_tail_fin
				.addOrReplaceChild("cube_r5",
						CubeListBuilder.create().texOffs(24, 3).addBox(-0.5F, -4.0F, 21.0F, 1.0F, 2.0F, 1.0F,
								new CubeDeformation(0.2F)),
						PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, -0.384F, 0.0F, 0.0F));

		PartDefinition top = end_tail_fin.addOrReplaceChild("top", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r6 = top.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(11, 83)
						.addBox(-0.5F, -7.0F, 23.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 110)
						.addBox(-0.5F, -6.0F, 22.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 103)
						.addBox(-0.5F, -5.0F, 21.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 95)
						.addBox(-0.5F, -4.0F, 20.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 85)
						.addBox(-0.5F, -3.0F, 19.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 74)
						.addBox(-0.5F, -2.0F, 18.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.384F, 0.0F, 0.0F));

		PartDefinition bottom = end_tail_fin.addOrReplaceChild("bottom", CubeListBuilder.create(),
				PartPose.offset(0.0F, 3.0F, -1.0F));

		PartDefinition cube_r7 = bottom.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(28, 69)
						.addBox(-0.5F, 3.0F, 23.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(16, 69)
						.addBox(-0.5F, 2.0F, 22.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(31, 76)
						.addBox(-0.5F, 1.0F, 21.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(31, 76)
						.addBox(-0.5F, 0.0F, 20.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(23, 89)
						.addBox(-0.5F, -1.0F, 19.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(19, 78)
						.addBox(-0.5F, -2.0F, 18.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.384F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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
		this.left_pants.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
	}
}