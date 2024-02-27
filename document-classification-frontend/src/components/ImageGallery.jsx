import React from "react";

function ImageGallery({ images }) {
  return (
    <div>
      {images.map((image) => (
        <img
          key={image}
          src={image}
          alt={image}
          style={{ width: "220px", height: "220px" }}
        />
      ))}
    </div>
  );
}

export default ImageGallery;
