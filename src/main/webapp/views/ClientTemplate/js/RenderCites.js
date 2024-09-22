
// Fetch the data from the URL
fetch(
    "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json"
)
    .then((response) => response.json())
    .then((data) => {
        // Get the reference to the select elements
        const provinceSelect = document.getElementById("province");
        const districtSelect = document.getElementById("district");
        const wardSelect = document.getElementById("ward");

        // Populate the province select
        data.forEach((province) => {
            const option = document.createElement("option");
            option.value = province.Id;
            option.text = province.Name;
            provinceSelect.add(option);
        });

        // Add event listener to province select
        provinceSelect.addEventListener("change", () => {
            // Clear the district and ward selects
            districtSelect.innerHTML = "<option value=''>Chọn quận</option>";
            wardSelect.innerHTML = "<option value=''>Chọn phường</option>";

            // Find the selected province
            const selectedProvince = data.find(
                (p) => p.Id === provinceSelect.value
            );

            // Populate the district select
            selectedProvince.Districts.forEach((district) => {
                const option = document.createElement("option");
                option.value = district.Id;
                option.text = district.Name;
                districtSelect.add(option);
            });
        });

        // Add event listener to district select
        districtSelect.addEventListener("change", () => {
            // Clear the ward select
            wardSelect.innerHTML = "<option value=''>Chọn phường</option>";

            // Find the selected province and district
            const selectedProvince = data.find(
                (p) => p.Id === provinceSelect.value
            );
            const selectedDistrict = selectedProvince.Districts.find(
                (d) => d.Id === districtSelect.value
            );

            // Populate the ward select
            selectedDistrict.Wards.forEach((ward) => {
                const option = document.createElement("option");
                option.value = ward.Id;
                option.text = ward.Name;
                wardSelect.add(option);
            });
        });
    })
    .catch((error) => console.error(error));
