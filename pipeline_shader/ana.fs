uniform sampler2D leftEye;
uniform sampler2D rightEye;

varying vec2 texCoord;

void main (void)
{
  vec4 left = texture2D(leftEye, texCoord);
  vec4 right = texture2D(rightEye, texCoord);

  gl_FragColor = vec4(left.r, right.gb, 1.0);
  //gl_FragColor = right;
}
